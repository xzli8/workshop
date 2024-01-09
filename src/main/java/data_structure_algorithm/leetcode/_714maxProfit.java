package data_structure_algorithm.leetcode;

public class _714maxProfit {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示第i天持股(j=1)/不持股(j=0)时的最大利润
         状态转移：("188.买卖股票的最佳时机III"中，k=无穷的情况，推导后可以把k这个维度约掉)
         dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i]);
         dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxProfit(int[] prices, int fee) {

            // 定义并初始化状态
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0] - fee;

            // 状态转移
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i] - fee);
            }
            return dp[n-1][0];
        }


    }

}
