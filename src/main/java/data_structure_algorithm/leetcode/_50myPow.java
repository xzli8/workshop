package data_structure_algorithm.leetcode;

public class _50myPow {

    public static class Solution0 {

        /**
         快速幂(迭代): O(logN), O(1)
         */
        public double myPow(double x, int n) {
            return n >= 0 ? qpow(x, n) : 1.0 / qpow(x, -(long) n);
        }

        private double qpow(double x, long n) {
            double res = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    res = res * x;
                }
                x = x * x;
                n >>= 1;
            }
            return res;
        }

    }

    public static class Solution1 {

        /**
         快速幂（递归）分治思想
         时间复杂度：O(logN)
         空间复杂度：O(logN)，递归栈的开销
         */
        public double myPow(double x, int n) {
            return n >= 0 ? quickPow(x, n) : 1.0 / quickPow(x, -n);
        }

        private double quickPow(double x, int n) {
            if (n == 0) return 1.0;
            return n % 2 == 0 ? quickPow(x * x, n/2) : quickPow(x * x, n/2) * x;
        }

    }

}
