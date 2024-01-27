package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _628maximumProduct {

    public static class Solution1 {

        /**
         排序 + 双指针
         时间复杂度：O(NlogN)
         空间复杂度：O(1)
         */
        public int maximumProduct(int[] nums) {
            // 边界情况
            int n = nums.length;
            if (n == 3) return nums[0] * nums[1] * nums[2];

            // 排序
            Arrays.sort(nums);
            return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[0] * nums[1] * nums[n - 1]);
        }

    }

}
