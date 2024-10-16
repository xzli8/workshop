package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _3194minimumAverage {

    public static class Solution1 {

        /**
         Sort + DoublePointer
         */
        public double minimumAverage(int[] nums) {
            // sort
            Arrays.sort(nums);

            // double pointer
            double min = Double.MAX_VALUE;
            int left = 0, right = nums.length - 1;
            while (left < right) {
                min = Math.min(min, (nums[left] + nums[right]) / 2.0);
                left++;
                right--;
            }
            return min;
        }

    }

}
