package data_structure_algorithm.leetcode;

public class _309maxProfit {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示第i天持股(i=1)/不持股(i=0)时的最大利润
         状态转移：("188.买卖股票的最佳时机III"中，k=无穷的情况，推导后可以把k这个维度约掉)
         dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxProfit(int[] prices) {

            int n = prices.length;
            if (n == 1) return 0;

            // 定义并初始化状态
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
            dp[1][1] = Math.max(dp[0][1], -prices[1]);

            // 状态转移
            for (int i = 2; i < n; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][0] - prices[i]);
            }
            return dp[n-1][0];
        }


    }

}
