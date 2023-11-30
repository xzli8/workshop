package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _45jump {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]表示跳到下标为i的位置的最小跳跃次数
         状态转移：for (j = 0; j < i; j++) if (j + nums[j] >= i) dp[i] = min(dp[j] + 1, dp[i])
         初始状态：dp[0] = 0

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int jump(int[] nums) {
             int n = nums.length;

             // 定义状态
             int[] dp = new int[n];

             // 初始状态
             Arrays.fill(dp, Integer.MAX_VALUE);
             dp[0] = 0;

             // 状态转移
             for (int i = 1; i < n; i++) {
                 for (int j = 0; j < i; j++) {
                     if (j + nums[j] >= i) {
                         dp[i] = Math.min(dp[j] + 1, dp[i]);
                     }
                 }
             }
             return dp[n-1];
         }

    }



    public static class Solution2 {

        /**
         贪心：每次都跳到最远的位置
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int jump(int[] nums) {
            int steps = 0, maxPosition = 0, end = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                // 找能跳到的最远位置
                maxPosition = Math.max(maxPosition, i + nums[i]);
                // 遇到边界时，更新边界，并且更新步数
                if (i == end) {
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }

    }



}
