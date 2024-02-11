package concurrent.producer_consumer;

import concurrent.producer_consumer.domain.Task;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition full = lock.newCondition(), empty = lock.newCondition();
        Queue<Task> queue = new ArrayDeque<>();
        int capacity = 5;

        // producer
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        while (queue.size() == capacity) {
                            System.out.println("Queue is full");
                            try {
                                full.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        queue.offer(new Task());
                        empty.signal();

                        try {
                            Thread.sleep(500 + new Random().nextInt(100));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                    } finally {
                        lock.unlock();
                    }
                }

            }, "Producer" + i).start();
        }

        // consumer
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        while (queue.isEmpty()) {
                            System.out.println("Queue is empty");
                            try {
                                empty.await();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        queue.poll();
                        full.signal();

                        try {
                            Thread.sleep(800 + new Random().nextInt(100));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Consumer" + i).start();
        }
    }

}
