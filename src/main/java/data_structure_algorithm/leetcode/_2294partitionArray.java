package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _2294partitionArray {

    public static class Solution1 {

        /**
         排序+贪心: O(NlogN, O(logN))
         */
        public int partitionArray(int[] nums, int k) {
            Arrays.sort(nums);
            int res = 1, min = nums[0];
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] - min > k) {
                    res++;
                    min = nums[i];
                }
            }
            return res;
        }

    }

}
