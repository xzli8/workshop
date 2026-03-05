package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _123maxProfit {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j][k]表示第i天持股(j=0)/不持股(j=1)最多允许k次交易时的最大利润
         状态转移：("188.买卖股票的最佳时机III"中，k=2的情况)
         dp[i][0][k] = max(dp[i-1][0][k], dp[i-1][1][k] + prices[i])
         dp[i][1][k] = max(dp[i-1][1][k], dp[i-1][0][k-1] - prices[i])

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxProfit(int[] prices) {
            int n = prices.length, k = 2;
            int[][][] dp = new int[n][2][k + 1];
            Arrays.fill(dp[0][0], 0);
            Arrays.fill(dp[0][1], -prices[0]);
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][0][j] = Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]);
                    dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
                }
            }
            return dp[n - 1][0][k];
        }

    }

}
