package concurrent.producer_consumer;

import concurrent.producer_consumer.domain.Task;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueue {

    public static void main(String[] args) {
        int capacity = 5;
        java.util.concurrent.BlockingQueue<Task> queue = new ArrayBlockingQueue<>(capacity);

        // producer
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        queue.put(new Task());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Thread.sleep(500 + new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                }
            }, "Producer" + i).start();
        }

        // consumer
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        Thread.sleep(800 + new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ", size: " + queue.size());
                }
            }, "Consumer" + i).start();
        }
    }

}
