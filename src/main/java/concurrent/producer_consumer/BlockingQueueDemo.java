package concurrent.producer_consumer;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1. 创建容量为5的阻塞队列
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);

        // 2. 线程1：put 1~10
        Thread putThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("【放入】" + i + "  队列大小：" + queue.size());
                }
                System.out.println("=== 放入线程执行完毕 ===");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "put-thread");

        // 3. 线程2：延迟1秒启动，take所有元素
        Thread takeThread = new Thread(() -> {
            try {
                // 延迟 1 秒启动
                Thread.sleep(1000);
                System.out.println("\n=== 取出线程启动 ===");

                // 一直取，直到取不到（这里会把1-10全部取完）
                for (int i = 0; i < 10; i++) {
                    int num = queue.take();
                    System.out.println("【取出】" + num + "  队列大小：" + queue.size());
                }
                System.out.println("=== 取出线程执行完毕 ===");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "take-thread");

        // 启动两个线程
        putThread.start();
        takeThread.start();
    }
}

// 阻塞队列实现（直接带上，方便你一键运行）
class MyBlockingQueue<E> {
    private final Object[] items;
    private int takeIndex;
    private int putIndex;
    private int count;
    private final Object lock = new Object();

    public MyBlockingQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        items = new Object[capacity];
    }

    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        synchronized (lock) {
            while (count == items.length) {
                lock.wait(); // 满了就阻塞
            }
            enqueue(e);
            lock.notifyAll();
        }
    }

    public E take() throws InterruptedException {
        synchronized (lock) {
            while (count == 0) {
                lock.wait(); // 空了就阻塞
            }
            E e = dequeue();
            lock.notifyAll();
            return e;
        }
    }

    private void enqueue(E e) {
        items[putIndex] = e;
        if (++putIndex == items.length) putIndex = 0;
        count++;
    }

    private E dequeue() {
        E e = (E) items[takeIndex];
        items[takeIndex] = null;
        if (++takeIndex == items.length) takeIndex = 0;
        count--;
        return e;
    }

    public int size() {
        synchronized (lock) {
            return count;
        }
    }
}