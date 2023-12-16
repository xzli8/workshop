package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class _1099twoSumLessThanK {

    /**
     * 题目描述：给你一个整数数组 nums 和整数 k ，返回最大和 sum ，满足存在 i < j 使得 nums[i] + nums[j] = sum 且 sum < k 。
     * 如果没有满足此等式的 i,j 存在，则返回 -1 。
     */

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(twoSumLessThanK(new int[] {34,23,1,24,75,33,54,8}, 60));
        }

        /**
         *  排序 + 双指针：(类似题："167.两数之和II-输入有序数组")
         *      时间复杂度：O(NlogN)，排序O(NlogN) + 双指针O(N)
         *      空间复杂度：O(1)
         */
        public int twoSumLessThanK(int[] nums, int k) {
            // 排序
            Arrays.sort(nums);

            // 双指针
            int left = 0, right = nums.length - 1, maxSum = Integer.MIN_VALUE;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < k) {
                    maxSum = Math.max(maxSum, sum);
                    left++;
                } else {
                    right--;
                }
            }
            return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
        }

    }

}
