package data_structure_algorithm.data_structure;


import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界阻塞队列（ReentrantLock + 两个 Condition，参考 ArrayBlockingQueue）
 *
 * ref: https://leetcode.cn/problems/design-bounded-blocking-queue/ (LeetCode 1188)
 *
 * 接口
 * - put / take：满了 / 空了就一直阻塞
 * - offer / poll（带超时）：超时返回 false / null
 * - size
 *
 * 设计要点
 * - 环形数组（head 出队、tail 入队、count 计数，不需要移动元素）
 * - 一把锁 + 两个 Condition（notFull 给生产者等，notEmpty 给消费者等）
 *     - 对比 synchronized + wait/notifyAll：只有一个等待队列，生产者和消费者混在一起，只能 notifyAll 全部唤醒
 * - 等待条件用 while 不用 if（虚假唤醒；被唤醒后锁可能先被别的线程抢到，条件又不成立了）
 * - 用 signal 不用 signalAll（放入一个元素只够一个消费者用，唤醒多个也只有一个能拿到）
 * - lockInterruptibly（等锁期间也能响应中断，方便线程池关闭）
 * - 不允许 null（poll 用 null 表示超时）
 */
public class BoundedBlockingQueue<E> {

    private final Object[] items;

    private int head, tail, count;

    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();

    private final Condition notEmpty = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be > 0");
        items = new Object[capacity];
    }

    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        lock.lockInterruptibly();
        try {
            // 满了就等
            while (count == items.length) {
                notFull.await();
            }
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                // 超时了还是满的
                if (nanos <= 0) return false;
                // 返回剩余等待时间，被唤醒但条件不成立时继续等剩下的时间
                nanos = notFull.awaitNanos(nanos);
            }
            enqueue(e);
            return true;
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            // 空了就等
            while (count == 0) {
                notEmpty.await();
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nanos <= 0) return null;
                nanos = notEmpty.awaitNanos(nanos);
            }
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        // 加锁保证读到最新的 count（也可以把 count 声明成 volatile）
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    // 调用方已持有锁
    private void enqueue(E e) {
        items[tail] = e;
        if (++tail == items.length) tail = 0;
        count++;
        // 队列非空了，唤醒一个消费者
        notEmpty.signal();
    }

    // 调用方已持有锁
    @SuppressWarnings("unchecked")
    private E dequeue() {
        E e = (E) items[head];
        // 置空，避免内存泄漏
        items[head] = null;
        if (++head == items.length) head = 0;
        count--;
        // 队列不满了，唤醒一个生产者
        notFull.signal();
        return e;
    }

    public static class TestBoundedBlockingQueue {

        @Test
        public void fifo() throws InterruptedException {
            BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<>(3);
            q.put(1);
            q.put(2);
            q.put(3);
            Assert.assertEquals(3, q.size());
            Assert.assertEquals(1, (int) q.take());
            // 环形数组绕回
            q.put(4);
            Assert.assertEquals(2, (int) q.take());
            Assert.assertEquals(3, (int) q.take());
            Assert.assertEquals(4, (int) q.take());
            Assert.assertEquals(0, q.size());
        }

        @Test
        public void timeout() throws InterruptedException {
            BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<>(1);
            // 空队列 poll 超时
            Assert.assertNull(q.poll(50, TimeUnit.MILLISECONDS));
            Assert.assertTrue(q.offer(1, 50, TimeUnit.MILLISECONDS));
            // 满队列 offer 超时
            Assert.assertFalse(q.offer(2, 50, TimeUnit.MILLISECONDS));
        }

        @Test
        public void putBlocksUntilTake() throws InterruptedException {
            BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<>(1);
            q.put(1);
            CountDownLatch done = new CountDownLatch(1);
            Thread producer = new Thread(() -> {
                try {
                    q.put(2);
                    done.countDown();
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            });
            producer.start();
            // 队列满，生产者应该还阻塞着
            Assert.assertFalse(done.await(100, TimeUnit.MILLISECONDS));
            Assert.assertEquals(1, (int) q.take());
            // 取走一个后生产者被唤醒
            Assert.assertTrue(done.await(1, TimeUnit.SECONDS));
            Assert.assertEquals(2, (int) q.take());
        }

        @Test
        public void interruptWhileWaiting() throws InterruptedException {
            BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<>(1);
            CountDownLatch interrupted = new CountDownLatch(1);
            Thread consumer = new Thread(() -> {
                try {
                    q.take();
                } catch (InterruptedException e) {
                    interrupted.countDown();
                }
            });
            consumer.start();
            Thread.sleep(50);
            consumer.interrupt();
            Assert.assertTrue(interrupted.await(1, TimeUnit.SECONDS));
        }

        @Test
        public void multiProducerMultiConsumer() throws InterruptedException {
            BoundedBlockingQueue<Integer> q = new BoundedBlockingQueue<>(4);
            int producers = 4, consumers = 4, perProducer = 10_000;
            ExecutorService pool = Executors.newFixedThreadPool(producers + consumers);
            AtomicLong sum = new AtomicLong();
            CountDownLatch finished = new CountDownLatch(producers + consumers);

            for (int p = 0; p < producers; p++) {
                pool.execute(() -> {
                    try {
                        for (int i = 1; i <= perProducer; i++) q.put(i);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        finished.countDown();
                    }
                });
            }
            for (int c = 0; c < consumers; c++) {
                pool.execute(() -> {
                    try {
                        // 每个消费者取的总数 = 生产总数 / 消费者数
                        for (int i = 0; i < producers * perProducer / consumers; i++) sum.addAndGet(q.take());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        finished.countDown();
                    }
                });
            }

            Assert.assertTrue(finished.await(10, TimeUnit.SECONDS));
            pool.shutdownNow();
            // 不丢不重：取出的总和等于放入的总和
            long expected = (long) producers * perProducer * (perProducer + 1) / 2;
            Assert.assertEquals(expected, sum.get());
            Assert.assertEquals(0, q.size());
        }
    }
}
