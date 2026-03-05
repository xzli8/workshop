package data_structure_algorithm.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class _1116ZeroEvenOdd {

    public static class Solution1 {

        /**
         信号量:O(N), O(1)
         */
        class ZeroEvenOdd {
            private int n;
            private Semaphore z = new Semaphore(1), e = new Semaphore(0), o = new Semaphore(0);

            public ZeroEvenOdd(int n) {
                this.n = n;
            }

            // printNumber.accept(x) outputs "x", where x is an integer.
            public void zero(IntConsumer printNumber) throws InterruptedException {
                for (int i = 0; i < n; i++) {
                    z.acquire(1);
                    printNumber.accept(0);
                    if (i % 2 == 0) {
                        o.release(1);
                    } else {
                        e.release(1);
                    }
                }
            }

            public void even(IntConsumer printNumber) throws InterruptedException {
                for (int i = 2; i <= n; i += 2) {
                    e.acquire(1);
                    printNumber.accept(i);
                    z.release(1);
                }
            }

            public void odd(IntConsumer printNumber) throws InterruptedException {
                for (int i = 1; i <= n; i += 2) {
                    o.acquire(1);
                    printNumber.accept(i);
                    z.release(1);
                }
            }

        }

    }

}
