package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _2465distinctAverages {

    public static class Solution1 {

        /**
         Sort + DoublePointer + Set
         */
        public int distinctAverages(int[] nums) {
            // sort
            Arrays.sort(nums);

            // double pointer
            Set<Double> distinctAverages = new HashSet<>();
            int left = 0, right = nums.length - 1;
            while (left < right) {
                double average = (nums[left] + nums[right]) / 2.0;
                distinctAverages.add(average);
                left++;
                right--;
            }
            return distinctAverages.size();
        }

    }

}
