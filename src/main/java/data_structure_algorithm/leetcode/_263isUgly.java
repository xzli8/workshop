package data_structure_algorithm.leetcode;

public class _263isUgly {

    public static class Solution1 {

        /**
         分析：num = 2^a * 3^b * 5^c，当余数为0时，不断除以因子{2, 3, 5}
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public boolean isUgly(int n) {
            if (n <= 0) return false;
            while (n % 2 == 0) n /= 2;
            while (n % 3 == 0) n /= 3;
            while (n % 5 == 0) n /= 5;
            return n == 1;
        }

    }

}
