package data_structure_algorithm.leetcode;

public class _581findUnsortedSubarray {

    public static class Solution1 {

        /**
         遍历寻找无序子数组的左右边界
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findUnsortedSubarray(int[] nums) {
            int n = nums.length, end = -1, start = 0, max = nums[0], min = nums[n - 1];
            for (int i = 0; i < n; i++) {
                // 更新右边界
                if (nums[i] < max) end = i;
                else max = nums[i];

                // 更新左边界
                if (nums[n - 1 - i] > min) start = n - 1 - i;
                else min = nums[n - 1 - i];
            }
            return end - start + 1;
        }

    }

}
