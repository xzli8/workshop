package data_structure_algorithm.leetcode;

public class _122maxProfit {

    public static class Solution1 {

        /**
         动态规划(类似题"376.摆动序列")
         定义状态：dp[i][0]表示第i天不持有股票的最大收益，dp[i][1]表示第i天持有股票的最大收益
         状态转移：
         dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
         初始状态：dp[0][0] = 0, dp[0][1] = -prices[0]

         时间复杂度：O(n)
         空间复杂度：O(n)
         */
         public int maxProfit(int[] prices) {
             // 定义状态
             int n = prices.length;
             int[][] dp = new int[n][2];

             // 初始状态
             dp[0][0] = 0;
             dp[0][1] = -prices[0];

             // 状态转移
             for (int i = 1; i < n; i++) {
                 dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                 dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
             }
             return dp[n-1][0];  // 不持有股票一定比持有股票收益高
         }

    }



    public static class Solution2 {

        /**
         贪心：总利润可以分解到每一天中去，计算第二天开始所有的每天利润，最后选择正利润累加，即可得到最大利润
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxProfit(int[] prices) {
            // 计算每天的利润，如果是正利润就累加
            int n = prices.length, maxProfit = 0;
            for (int i = 1; i < n; i++) {
                int profit = prices[i] - prices[i - 1];
                if (profit > 0) {
                    maxProfit += profit;
                }
            }
            return maxProfit;
        }

    }


}
