package concurrent.producer_consumer.wait_notify;

import concurrent.producer_consumer.Task;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

    private final Queue<Task> queue;

    private final int capacity;

    public Producer(Queue<Task> queue, int capacity) {
        this.queue = queue;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == capacity) {
                    try {
                        System.out.println("Queue is full");
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
    }

}
