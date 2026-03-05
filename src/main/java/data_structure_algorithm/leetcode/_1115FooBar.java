package data_structure_algorithm.leetcode;

import java.util.concurrent.Semaphore;

public class _1115FooBar {

    public static class Solution1 {


        /**
         信号量: O(N), O(1)
         ref: https://leetcode.doocs.org/lc/1115/
         */
        class FooBar {
            private int n;
            private Semaphore f = new Semaphore(1), b = new Semaphore(0);   // 保证foo比bar先打印

            public FooBar(int n) {
                this.n = n;
            }

            public void foo(Runnable printFoo) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    f.acquire(1);
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    b.release(1);
                }
            }

            public void bar(Runnable printBar) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    b.acquire(1);
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    f.release(1);
                }
            }
        }

    }

}
