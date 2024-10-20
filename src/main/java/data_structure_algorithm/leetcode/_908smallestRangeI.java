package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _908smallestRangeI {

    public static class Solution1 {

        /**
         Math
         */
        public int smallestRangeI(int[] nums, int k) {
            int min = Arrays.stream(nums).min().getAsInt();
            int max = Arrays.stream(nums).max().getAsInt();
            return max - min <= 2 * k ? 0 : max - min - 2 * k;
        }

    }

}
