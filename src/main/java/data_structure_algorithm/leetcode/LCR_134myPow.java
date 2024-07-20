package data_structure_algorithm.leetcode;

public class LCR_134myPow {

    public static class Solution1 {

        /**
         分治
         */
        public double myPow(double x, int n) {
            return n >= 0? doMyPow(x, n) : 1.0 / doMyPow(x, -n);
        }

        private double doMyPow(double x, int n) {
            if (n == 0) return 1.0;
            return n % 2 == 0 ? doMyPow(x * x, n / 2) : doMyPow(x * x, n / 2) * x;
        }

    }

}
