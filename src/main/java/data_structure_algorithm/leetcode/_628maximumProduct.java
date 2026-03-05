package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _628maximumProduct {

    public static class Solution1 {

        /**
         一次遍历：O(N), O(1)
         */
        public int maximumProduct(int[] nums) {
            int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
            int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
            for (int num : nums) {
                if (num < min1) {
                    min2 = min1;
                    min1 = num;
                } else if (num < min2) {
                    min2 = num;
                }

                if (num > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = num;
                } else if (num > max2) {
                    max3 = max2;
                    max2 = num;
                } else if (num > max3) {
                    max3 = num;
                }
            }
            return Math.max(max1 * max2 * max3, min1 * min2 * max1);
        }

    }

    public static class Solution2 {

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
