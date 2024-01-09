package data_structure_algorithm.leetcode;

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

            // 定义并初始化状态(k = 0, i = 0)
            int n = prices.length;
            int[][][] dp = new int[n][2][3];
            for (int i = 0; i < n; i++) {   // k = 0
                dp[i][0][0] = 0;
                dp[i][1][0] = 0;
            }
            for (int k = 1; k <= 2; k++) {  // i = 0
                dp[0][0][k] = 0;
                dp[0][1][k] = -prices[0];
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int k = 1; k <= 2; k++) {
                    dp[i][0][k] = Math.max(dp[i-1][0][k], dp[i-1][1][k] + prices[i]);
                    dp[i][1][k] = Math.max(dp[i-1][1][k], dp[i-1][0][k-1] - prices[i]);
                }
            }
            return dp[n-1][0][2];
        }


    }

}
