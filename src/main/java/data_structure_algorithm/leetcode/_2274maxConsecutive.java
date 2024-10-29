package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _2274maxConsecutive {

    public static class Solution1 {

        /**
         Sort
         TimeComplexity: O(NlogN)
         SpaceComplexity: O(N)
         */
        public int maxConsecutive(int bottom, int top, int[] special) {
            // sort
            Arrays.sort(special);

            // find max
            int n = special.length, max = Math.max(special[0] - bottom, top - special[n - 1]);
            for (int i = 1; i < n; i++) {
                max = Math.max(max, special[i] - special[i - 1] - 1);
            }
            return max;
        }

    }

}
