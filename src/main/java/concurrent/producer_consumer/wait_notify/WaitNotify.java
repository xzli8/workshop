package concurrent.producer_consumer.wait_notify;

import concurrent.producer_consumer.Task;

import java.util.ArrayDeque;
import java.util.Queue;

public class WaitNotify {

    private static final Queue<Task> queue = new ArrayDeque<>();

    private static final int capacity = 5;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Producer(queue, capacity), "Producer" + i).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Consumer(queue), "Consumer" + i).start();
        }
    }

}
