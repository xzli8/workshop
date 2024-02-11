package concurrent.interrupt;

public class Interrupt {

    /**
     * ref:https://blog.csdn.net/xzli8_geo/article/details/132448720?spm=1001.2014.3001.5501
     */

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println("running " + System.currentTimeMillis());
            }
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }

}
