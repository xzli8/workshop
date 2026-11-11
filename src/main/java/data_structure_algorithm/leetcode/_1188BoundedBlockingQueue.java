package data_structure_algorithm.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1188BoundedBlockingQueue {

    /**
     * ref: https://www.cnblogs.com/Dylan-Java-NYC/p/12302134.html
     */
    public static class Solution1 {

        /**
         * 循环数组 + 锁/wait-notify
         */
        class BoundedBlockingQueue {

            private int[] items;

            private int count = 0, head = 0, tail = 0;

            private final Lock lock = new ReentrantLock();

            private final Condition notFull = lock.newCondition();

            private final Condition notEmpty = lock.newCondition();

            public BoundedBlockingQueue(int capacity) {
                items = new int[capacity];
            }

            public void enqueue(int element) throws InterruptedException {
                lock.lock();
                try {
                    while (count == items.length) notFull.await();
                    items[tail++] = element;
                    if (tail >= items.length) tail = 0;
                    count++;
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }

            public int dequeue() throws InterruptedException {
                lock.lock();
                try {
                    while (count == 0) notEmpty.await();
                    int element = items[head++];
                    if (head == items.length) head = 0;
                    count--;
                    notFull.signal();
                    return element;
                } finally {
                    lock.unlock();
                }
            }

            public int size() {
                lock.lock();
                try {
                    return count;
                } finally {
                    lock.unlock();
                }
            }

        }

    }

    public static class Solution2 {

        /**
         * 循环数组 + synchronized + wait/notifyAll
         * - 只有一个等待队列，生产者和消费者混在一起
         * - 必须用 notifyAll：用 notify 可能唤醒的是同类线程（比如生产者唤醒了另一个生产者），对方继续等，真正该醒的线程没被唤醒，所有线程都卡住
         * - 代价：每次都唤醒所有线程，大部分醒来发现条件不满足又继续等
         */
        class BoundedBlockingQueue {

            private final int[] items;

            private int count = 0, head = 0, tail = 0;

            public BoundedBlockingQueue(int capacity) {
                items = new int[capacity];
            }

            public synchronized void enqueue(int element) throws InterruptedException {
                while (count == items.length) wait();
                items[tail] = element;
                if (++tail == items.length) tail = 0;
                count++;
                notifyAll();
            }

            public synchronized int dequeue() throws InterruptedException {
                while (count == 0) wait();
                int element = items[head];
                if (++head == items.length) head = 0;
                count--;
                notifyAll();
                return element;
            }

            public synchronized int size() {
                return count;
            }

        }

    }

    public static class Solution3 {

        /**
         * 两个 Semaphore + 互斥锁
         * - emptySlots：空位数，初始 capacity；生产者先 acquire，没有空位就阻塞
         * - filledSlots：元素数，初始 0；消费者先 acquire，没有元素就阻塞
         * - 信号量只负责"能不能进"，修改数组本身还要一把互斥锁（可能有多个生产者同时拿到空位）
         * - 顺序：先 acquire 信号量，再加锁；反过来会持有锁去阻塞等待，导致死锁
         */
        class BoundedBlockingQueue {

            private final int[] items;

            private int count = 0, head = 0, tail = 0;

            private final Semaphore emptySlots, filledSlots = new Semaphore(0);

            private final Lock lock = new ReentrantLock();

            public BoundedBlockingQueue(int capacity) {
                items = new int[capacity];
                emptySlots = new Semaphore(capacity);
            }

            public void enqueue(int element) throws InterruptedException {
                emptySlots.acquire();
                lock.lock();
                try {
                    items[tail] = element;
                    if (++tail == items.length) tail = 0;
                    count++;
                } finally {
                    lock.unlock();
                }
                filledSlots.release();
            }

            public int dequeue() throws InterruptedException {
                filledSlots.acquire();
                int element;
                lock.lock();
                try {
                    element = items[head];
                    if (++head == items.length) head = 0;
                    count--;
                } finally {
                    lock.unlock();
                }
                emptySlots.release();
                return element;
            }

            public int size() {
                lock.lock();
                try {
                    return count;
                } finally {
                    lock.unlock();
                }
            }

        }

    }

}
