package data_structure_algorithm.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _1188BoundedBlockingQueue {

    /**
     * ref: https://www.cnblogs.com/Dylan-Java-NYC/p/12302134.html
     */
    public static class Solution1 {

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

}
