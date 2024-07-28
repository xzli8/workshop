package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _2740findValueOfPartition {

    public static class Solution1 {

        /**
         Sort
         */
        public int findValueOfPartition(int[] nums) {
            // sort
            Arrays.sort(nums);

            // find mininal value
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length - 1; i++) {
                min = Math.min(min, nums[i + 1] - nums[i]);
            }
            return min;
        }

    }

}
