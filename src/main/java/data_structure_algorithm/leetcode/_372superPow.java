package data_structure_algorithm.leetcode;

public class _372superPow {

    public static class Solution1 {

        /**
         分治：将数组b分解，逐位计算
         求模公式：(a * b) % k = (a % k)(b % k) % k
         时间复杂度：O(N)
         空间复杂度：O(N)，递归栈的开销
         */
        public int superPow(int a, int[] b) {
            return doSuperPow(a, b, b.length - 1);
        }

        private int base = 1337;

        private int doSuperPow(int a, int[] b, int n) {
            if (n == -1) return 1;

            int part1 = powMod(a, b[n]);
            int part2 = powMod(doSuperPow(a, b, n - 1), 10);
            return (part1 % base) * (part2 % base) % base;
        }

        /**
         计算a^k对base取余的结果
         NOTE：为了更高效，这里可以换用快速幂(50.Pow(x, n))，但不是很有必要，因为k的范围是[0,9]
         */
        private int powMod(int a, int k) {
            a %= base;
            int res = 1;
            for (int i = 0; i < k; i++) {
                res *= a;
                res %= base;
            }
            return res;
        }

    }

}
