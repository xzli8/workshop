package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _135candy {

    public static class Solution1 {

        /**
         贪心
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int candy(int[] ratings) {
            int n = ratings.length;
            int[] left = new int[n], right = new int[n];
            Arrays.fill(left, 1);
            Arrays.fill(right, 1);

            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i-1]) left[i] = left[i-1] + 1;
            }
            for (int i = n-2; i >= 0; i--) {
                if (ratings[i] > ratings[i+1]) right[i] = right[i+1] + 1;
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                count += Math.max(left[i], right[i]);
            }
            return count;
        }

    }



}
