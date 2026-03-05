package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _3573maximumProfit {

    public static class Solution1 {

        /**
         DP: O(N * K), O(N * K)
         Ref: https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-v/solutions/3858831/python3javacgotypescript-yi-ti-yi-jie-do-snx3/
         */
        public long maximumProfit(int[] prices, int k) {
            int n = prices.length;

            // 定义状态：
            // dp[i][0][j]表示第i天不持股至多完成j笔交易的最大利润
            // dp[i][1][j]表示第i天持股至多完成j笔交易的最大利润
            // dp[i][2][j]表示第i天处于做空时至多完成j笔交易的最大利润
            long[][][] dp = new long[n][3][k + 1];

            // 初始状态
            Arrays.fill(dp[0][0], 0);
            Arrays.fill(dp[0][1], -prices[0]);
            Arrays.fill(dp[0][2], prices[0]);

            // 状态转移(买入的时候计算交易次数，卖出的时候不计算交易次数)
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][0][j] = Math.max(Math.max(dp[i - 1][0][j], dp[i - 1][1][j] + prices[i]), dp[i - 1][2][j] - prices[i]);
                    dp[i][1][j] = Math.max(dp[i - 1][1][j], dp[i - 1][0][j - 1] - prices[i]);
                    dp[i][2][j] = Math.max(dp[i - 1][2][j], dp[i - 1][0][j - 1] + prices[i]);
                }
            }
            return dp[n - 1][0][k];
        }

    }

}
