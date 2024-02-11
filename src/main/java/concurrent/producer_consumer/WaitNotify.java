package concurrent.producer_consumer;

import concurrent.producer_consumer.domain.Task;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class WaitNotify {

    public static void main(String[] args) {
        Queue<Task> queue = new ArrayDeque<>();
        int capacity = 5;

        // producer
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (queue) {
                        while (queue.size() == capacity) {
                            System.out.println("Queue is full");
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        queue.offer(new Task());
                        queue.notifyAll();

                        try {
                            Thread.sleep(500 + new Random().nextInt(100));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                    }
                }
            }, "Producer" + i).start();
        }

        // consumer
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (queue) {
                        while (queue.isEmpty()) {
                            System.out.println("Queue is empty");
                            try {
                                queue.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        queue.poll();
                        queue.notifyAll();

                        try {
                            Thread.sleep(800 + new Random().nextInt(100));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                    }
                }
            }, "Consumer" + i).start();
        }
    }

}
