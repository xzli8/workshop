package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _204countPrimes {

    public static class Solution1 {

    }



    public static class Solution2 {

        /**
         厄拉多塞筛法
         时间复杂度：O(NloglogN)
         空间复杂度：O(N)
         */
        public int countPrimes(int n) {
            // 将所有的数都初始化为质数
            boolean[] isPrimes = new boolean[n];
            Arrays.fill(isPrimes, true);

            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrimes[i]) {
                    count++;
                }
                // 将当前数的所有倍数都标记为合数
                for (int j = i + i; j < n; j += i) {
                    isPrimes[j] = false;
                }
            }
            return count;
        }

    }


}
