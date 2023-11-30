package data_structure_algorithm.leetcode;

public class _53maxSubArray {



    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]表示以nums[i]结尾的子数组的最大和
         状态转移：dp[i] = max(nums[i], dp[i - 1] + nums[i]);
         初始状态：dp[0] = nums[0]

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int maxSubArray(int[] nums) {
             // 定义状态
             int n = nums.length;
             int[] dp = new int[n];

             // 初始状态
             dp[0] = nums[0];

             // 状态转移
             int max = dp[0];
             for (int i = 1; i < n; i++) {
                 dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
                 max = Math.max(max, dp[i]);
             }
             return max;
         }

    }



    public static class Solution2 {

        /**
         空间压缩：dp[i]只与dp[i - 1]有关
         */
        public int maxSubArray(int[] nums) {
            int dp = nums[0];
            int max = dp;
            for (int i = 1; i < nums.length; i++) {
                dp = Math.max(nums[i], dp + nums[i]);
                max = Math.max(dp, max);
            }
            return max;
        }

    }



    public static class Solution3 {

        /**
         贪心
         局部最优：当前“连续和”为负数的时候立刻放弃，从下一个元素重新计算“连续和”。
         因为负数加上下一个元素 “连续和”只会越来越小。
         全局最优：选取最大“连续和”。

         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxSubArray(int[] nums) {
            int max = Integer.MIN_VALUE, sum = 0;
            for (int num : nums) {
                sum += num;
                max = Math.max(max, sum);
                if (sum < 0) {
                    sum = 0;
                }
            }
            return max;
        }

    }



}
