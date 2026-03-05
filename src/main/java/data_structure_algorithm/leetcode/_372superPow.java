package data_structure_algorithm.leetcode;

public class _372superPow {


    /**
     * 快速幂: O(logN), O(1)
     * ref: https://leetcode.doocs.org/lc/372/#_3
     */
    public static class Solution1 {

        /**
         快速幂: O(logN), O(1)
         */
        public int superPow(int a, int[] b) {
            long res = 1, num = a;
            for (int i = b.length - 1; i >= 0; i--) {
                res = res * qpow(num, b[i]) % mod;
                num = qpow(num, 10);
            }
            return (int) res;
        }

        private int mod = 1337;

        private long qpow(long a, int n) {
            long res = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    res = res * a % mod;
                }
                a = a * a % mod;
                n >>= 1;
            }
            return res;
        }
    }

    public static class Solution2 {

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
            NOTE：
                1.这里一边计算一边取余，也可以先计算处结果再取余；
                2.为了更高效，这里可以换用快速幂(50.Pow(x, n))，但不是很有必要，因为k的范围是[0,9]；
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
