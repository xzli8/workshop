package data_structure_algorithm.data_structure;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 延迟任务调度器（简化版 ScheduledThreadPoolExecutor，LeetCode 没有对应题）
 *
 * 接口
 * - schedule(task, delay, unit)：延迟 delay 后执行一次
 * - scheduleAtFixedRate(task, initialDelay, period, unit)：周期执行
 * - Handle.cancel()：取消
 * - shutdown()
 *
 * 设计要点
 * - 小顶堆（PriorityQueue）按触发时间排序，堆顶就是最早到期的任务
 *     - 触发时间相同时按提交序号排，保证先提交先执行
 * - 一个调度线程 + 一个 Condition
 *     - 堆空：await，等新任务
 *     - 堆顶未到期：awaitNanos(堆顶剩余时间)，到点自己醒
 *     - 堆顶到期：取出，交给工作线程池执行
 * - 新任务成为堆顶时 signal 调度线程（关键点）
 *     - 调度线程可能正在等一个更晚的任务，不唤醒的话新任务会被延误
 * - 调度线程只负责取任务，执行交给线程池，慢任务不影响其他任务准时触发
 * - 时间用 System.nanoTime（单调递增），不用 currentTimeMillis（系统时间可能被调整）
 * - 取消用标记 + 惰性删除：cancel O(1)，调度线程取出时跳过（从堆中间删除要 O(n)）
 * - 周期任务按固定频率（fixed rate）：下次触发时间 = 本次触发时间 + period，取出时就放回堆里，不等执行完
 *     - 对比 fixed delay：下次 = 本次执行结束时间 + period，要等执行完再放回
 *
 * 扩展追问
 * - 多个调度线程：JDK DelayQueue 用 leader-follower，只有 leader 定时等待堆顶，其他线程无限期 await，避免多个线程同时醒来抢同一个任务
 * - 任务量极大、大量取消：改用时间轮（插入和取消 O(1)，精度到一个 tick）
 * - 分布式 / 持久化：见 docs/system-design/jobScheduler
 */
public class DelayedTaskScheduler {

    public interface Handle {
        void cancel();

        boolean isCancelled();
    }

    private static class ScheduledTask implements Handle, Comparable<ScheduledTask> {
        final Runnable task;
        // 周期，单次任务为 0
        final long periodNanos;
        // 提交序号，触发时间相同时保证先进先出
        final long seq;
        // 下次触发时间（nanoTime），只在持有锁时读写
        long triggerTime;
        volatile boolean cancelled;

        ScheduledTask(Runnable task, long triggerTime, long periodNanos, long seq) {
            this.task = task;
            this.triggerTime = triggerTime;
            this.periodNanos = periodNanos;
            this.seq = seq;
        }

        @Override
        public void cancel() {
            cancelled = true;
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public int compareTo(ScheduledTask o) {
            // 用差值比较，避免 nanoTime 溢出时比较出错
            long diff = triggerTime - o.triggerTime;
            if (diff != 0) return diff < 0 ? -1 : 1;
            return Long.compare(seq, o.seq);
        }
    }

    private final PriorityQueue<ScheduledTask> heap = new PriorityQueue<>();

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition available = lock.newCondition();

    private final AtomicLong seqGen = new AtomicLong();

    private final ExecutorService workers;

    private final Thread dispatcher;

    private volatile boolean shutdown;

    public DelayedTaskScheduler(int workerThreads) {
        workers = Executors.newFixedThreadPool(workerThreads);
        dispatcher = new Thread(this::dispatchLoop, "delayed-task-dispatcher");
        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public Handle schedule(Runnable task, long delay, TimeUnit unit) {
        return enqueue(task, unit.toNanos(delay), 0);
    }

    public Handle scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit unit) {
        if (period <= 0) throw new IllegalArgumentException("period must be > 0");
        return enqueue(task, unit.toNanos(initialDelay), unit.toNanos(period));
    }

    public void shutdown() {
        shutdown = true;
        lock.lock();
        try {
            heap.clear();
            available.signal();
        } finally {
            lock.unlock();
        }
        workers.shutdown();
    }

    private Handle enqueue(Runnable task, long delayNanos, long periodNanos) {
        if (task == null) throw new NullPointerException();
        if (shutdown) throw new IllegalStateException("scheduler is shut down");
        ScheduledTask st = new ScheduledTask(task, System.nanoTime() + Math.max(0, delayNanos), periodNanos, seqGen.getAndIncrement());
        lock.lock();
        try {
            heap.offer(st);
            // 新任务成了堆顶：调度线程可能在等一个更晚的任务，唤醒它重新计算等待时间
            if (heap.peek() == st) available.signal();
        } finally {
            lock.unlock();
        }
        return st;
    }

    private void dispatchLoop() {
        while (!shutdown) {
            ScheduledTask due;
            lock.lock();
            try {
                ScheduledTask head = heap.peek();
                if (head == null) {
                    available.await();
                    continue;
                }
                // 惰性删除已取消的任务
                if (head.cancelled) {
                    heap.poll();
                    continue;
                }
                long waitNanos = head.triggerTime - System.nanoTime();
                if (waitNanos > 0) {
                    // 醒来后重新看堆顶：可能是到点了，也可能是来了更早的新任务
                    available.awaitNanos(waitNanos);
                    continue;
                }
                due = heap.poll();
                // 周期任务：按固定频率算出下次触发时间，立即放回堆里
                if (due.periodNanos > 0) {
                    due.triggerTime += due.periodNanos;
                    heap.offer(due);
                }
            } catch (InterruptedException e) {
                return;
            } finally {
                lock.unlock();
            }
            runOnWorker(due);
        }
    }

    private void runOnWorker(ScheduledTask st) {
        try {
            workers.execute(() -> {
                if (st.cancelled) return;
                try {
                    st.task.run();
                } catch (RuntimeException e) {
                    // 一个任务抛异常不影响其他任务；周期任务继续调度（JDK 的做法是抛异常后停止该周期任务）
                    System.err.println("task failed: " + e);
                }
            });
        } catch (java.util.concurrent.RejectedExecutionException ignored) {
            // 已经 shutdown
        }
    }

    public static class TestDelayedTaskScheduler {

        @Test
        public void runsInTriggerOrder() throws InterruptedException {
            DelayedTaskScheduler s = new DelayedTaskScheduler(2);
            List<Integer> order = new CopyOnWriteArrayList<>();
            CountDownLatch done = new CountDownLatch(3);
            // 提交顺序和触发顺序不同
            s.schedule(() -> { order.add(300); done.countDown(); }, 300, TimeUnit.MILLISECONDS);
            s.schedule(() -> { order.add(100); done.countDown(); }, 100, TimeUnit.MILLISECONDS);
            s.schedule(() -> { order.add(200); done.countDown(); }, 200, TimeUnit.MILLISECONDS);
            Assert.assertTrue(done.await(2, TimeUnit.SECONDS));
            Assert.assertEquals(java.util.Arrays.asList(100, 200, 300), order);
            s.shutdown();
        }

        @Test
        public void earlierTaskWakesDispatcher() throws InterruptedException {
            DelayedTaskScheduler s = new DelayedTaskScheduler(1);
            // 调度线程先在等一个 5s 后的任务
            s.schedule(() -> {}, 5, TimeUnit.SECONDS);
            Thread.sleep(50);
            CountDownLatch done = new CountDownLatch(1);
            long start = System.nanoTime();
            s.schedule(done::countDown, 50, TimeUnit.MILLISECONDS);
            // 新任务更早到期，必须被及时执行，而不是等到 5s 后
            Assert.assertTrue(done.await(1, TimeUnit.SECONDS));
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
            Assert.assertTrue("elapsed " + elapsedMs + "ms", elapsedMs >= 50 && elapsedMs < 500);
            s.shutdown();
        }

        @Test
        public void cancelledTaskDoesNotRun() throws InterruptedException {
            DelayedTaskScheduler s = new DelayedTaskScheduler(1);
            AtomicInteger runs = new AtomicInteger();
            Handle h = s.schedule(runs::incrementAndGet, 100, TimeUnit.MILLISECONDS);
            h.cancel();
            Thread.sleep(250);
            Assert.assertEquals(0, runs.get());
            Assert.assertTrue(h.isCancelled());
            s.shutdown();
        }

        @Test
        public void fixedRateRepeatsUntilCancelled() throws InterruptedException {
            DelayedTaskScheduler s = new DelayedTaskScheduler(1);
            AtomicInteger runs = new AtomicInteger();
            CountDownLatch threeRuns = new CountDownLatch(3);
            Handle h = s.scheduleAtFixedRate(() -> { runs.incrementAndGet(); threeRuns.countDown(); }, 0, 50, TimeUnit.MILLISECONDS);
            Assert.assertTrue(threeRuns.await(1, TimeUnit.SECONDS));
            h.cancel();
            int afterCancel = runs.get();
            Thread.sleep(200);
            // 取消时可能有一次已经交给线程池，最多再多跑一次
            Assert.assertTrue(runs.get() <= afterCancel + 1);
            s.shutdown();
        }

        @Test
        public void slowTaskDoesNotDelayOthers() throws InterruptedException {
            DelayedTaskScheduler s = new DelayedTaskScheduler(2);
            CountDownLatch fastDone = new CountDownLatch(1);
            s.schedule(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }, 0, TimeUnit.MILLISECONDS);
            long start = System.nanoTime();
            s.schedule(fastDone::countDown, 50, TimeUnit.MILLISECONDS);
            // 慢任务在工作线程里跑，不阻塞调度线程
            Assert.assertTrue(fastDone.await(500, TimeUnit.MILLISECONDS));
            Assert.assertTrue(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start) < 500);
            s.shutdown();
        }

        @Test
        public void shutdownRejectsNewTasks() {
            DelayedTaskScheduler s = new DelayedTaskScheduler(1);
            s.shutdown();
            try {
                s.schedule(() -> {}, 10, TimeUnit.MILLISECONDS);
                Assert.fail("should reject after shutdown");
            } catch (IllegalStateException expected) {
                // ok
            }
        }
    }
}
