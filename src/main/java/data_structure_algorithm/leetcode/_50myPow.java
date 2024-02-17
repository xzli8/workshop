package data_structure_algorithm.leetcode;

public class _50myPow {

    public static class Solution0 {

        /**
         分治
         时间复杂度：O(logN)
         空间复杂度：O(logN)
         */
        public double myPow(double x, int n) {
            return n >= 0 ? doMyPow(x, n) : 1.0 / doMyPow(x, -n);
        }

        private double doMyPow(double x, int n) {
            if (n == 0) return 1.0;
            return n % 2 == 0 ? doMyPow(x * x, n / 2) : doMyPow(x * x, n / 2) * x;
        }

    }

    public static class Solution1 {

        /**
         分治（快速幂）
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
