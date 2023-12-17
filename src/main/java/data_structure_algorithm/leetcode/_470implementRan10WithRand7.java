package data_structure_algorithm.leetcode;

import java.util.Random;

public class _470implementRan10WithRand7 {

    public static class Solution1 {

        class SolBase {

            private Random random = new Random();

            public int rand7() {
                return random.nextInt(7);
            }

        }

        /**
         * The rand7() API is already defined in the parent class SolBase.
         * public int rand7();
         * @return a random integer in the range 1 to 7
         */
        class Solution extends SolBase {

            /**
             1.拒绝采样
             知识：(randX - 1) * Y + randY 可以等概率生成[1, X * Y]内的数字
             */
            public int rand10() {
                while (true) {
                    int a = rand7();
                    int b = rand7();
                    int num = (a - 1) * 7 + b;  // rand49
                    if (num <= 40) return num % 10 + 1; // 拒绝采样

                    a = num - 40;   // rand9
                    b = rand7();
                    num = (a - 1) * 7 + b;  // rand63
                    if (num <= 60) return num % 10 + 1; // 拒绝采样

                    a = num - 60;   // rand3
                    b = rand7();
                    num = (a - 1) * 7 + b;  // rand21
                    if (num <= 20) return num % 10 + 1;
                }
            }

        }


    }


}
