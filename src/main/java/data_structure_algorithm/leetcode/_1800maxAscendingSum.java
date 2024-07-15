package data_structure_algorithm.leetcode;

public class _1800maxAscendingSum {

    public static class Solution1 {

        /**
         贪心：不满足的数字是下一个序列的起点
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxAscendingSum(int[] nums) {
            int n = nums.length, i = 1, maxSum = 0;
            while (i - 1 < n) {
                int sum = nums[i - 1];
                while (i < n && nums[i] > nums[i - 1]) {
                    sum += nums[i];
                    i++;
                }
                maxSum = Math.max(maxSum, sum);
                i++;
            }
            return maxSum;
        }

    }

}
