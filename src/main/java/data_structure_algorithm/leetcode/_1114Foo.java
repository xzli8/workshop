package data_structure_algorithm.leetcode;

import java.util.concurrent.Semaphore;

public class _1114Foo {

    public static class Solution1 {

        /**
         信号量:O(1), O(1)
         */
        class Foo {

            private Semaphore a = new Semaphore(1), b = new Semaphore(0), c = new Semaphore(0);

            public Foo() {
            }

            public void first(Runnable printFirst) throws InterruptedException {
                a.acquire(1);
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                b.release(1);
            }

            public void second(Runnable printSecond) throws InterruptedException {
                b.acquire(1);
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                c.release(1);
            }

            public void third(Runnable printThird) throws InterruptedException {
                c.acquire(1);
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                a.release(1);
            }
        }

    }

}
