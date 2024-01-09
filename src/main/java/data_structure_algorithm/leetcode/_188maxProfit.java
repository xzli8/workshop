package data_structure_algorithm.leetcode;

public class _188maxProfit {

    /**
     * ref:https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/yi-ge-fang-3b01b/
     */

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j][k]表示第i天，持有股票(j=1)或不持有股票(j=0)，最多完成k笔交易时的最大利润
         状态转移：
         dp[i][0][k] = max(dp[i-1][0][k], dp[i-1][1][k] + price[i])
         dp[i][1][k] = max(dp[i-1][1][k], dp[i-1][0][k-1] - price[i])
         初始状态：i=0和k=0

         时间复杂度：O(K*N)
         空间复杂度：O(K*N)
         */
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;

            // 初始化状态(k = 0, i = 0)
            int[][][] dp = new int[n][2][k+1];
            for (int i = 0; i < n; i++) {   // k = 0
                dp[i][0][0] = 0;
                dp[i][1][0] = 0;
            }
            for (int j = 1; j <= k; j++) {  // i = 0
                dp[0][0][j] = 0;
                dp[0][1][j] = -prices[0];
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = 1; j <= k; j++) {
                    dp[i][0][j] = Math.max(dp[i-1][0][j], dp[i-1][1][j] + prices[i]);
                    dp[i][1][j] = Math.max(dp[i-1][1][j], dp[i-1][0][j-1] - prices[i]);
                }
            }
            return dp[n-1][0][k];
        }


    }

}
