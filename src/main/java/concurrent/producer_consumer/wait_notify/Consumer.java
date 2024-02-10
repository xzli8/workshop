package concurrent.producer_consumer.wait_notify;

import concurrent.producer_consumer.Task;

import java.util.Queue;
import java.util.Random;

public class Consumer implements Runnable {

    private final Queue<Task> queue;

    public Consumer(Queue<Task> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        System.out.println("Queue if empty");
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
    }

}
