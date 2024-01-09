package data_structure_algorithm.leetcode;

public class _121maxProfit {

    /**
     * ref:https://labuladong.gitee.io/algo/di-ling-zh-bfe1b/yi-ge-fang-3b01b/
     */

    public static class Solution1 {

        /**
         动态规划：
         定义状态：dp[i]表示前i天的最大收益
         状态转移：dp[i] = max(dp[i-1], prices[i]-minPrice)，其中minPrice表示前i天中的最低价格
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
         public int maxProfit(int[] prices) {
             int n = prices.length;
             int[] dp = new int[n];
             for (int i = 0; i < n; i++) dp[i] = 0;

             int minPrice = prices[0];
             for (int i = 1; i < n; i++) {
                 if (prices[i] < minPrice) minPrice = prices[i];
                 dp[i] = Math.max(dp[i-1], prices[i]-minPrice);
             }
             return dp[n-1];
         }


    }



    public static class Solution2 {

        /**
         动态规划（优化空间）：用一个变量而不是数组记录前i天的最大收益
         空间复杂度：O(1)
         */
         public int maxProfit(int[] prices) {
             int dp = 0, minPrice = prices[0];
             for (int i = 1; i < prices.length; i++) {
                 minPrice = Math.min(minPrice, prices[i]);
                 dp = Math.max(dp, prices[i] - minPrice);
             }
             return dp;
         }


    }



    public static class Solution3 {

        /**
         动态规划
         定义状态：dp[i][j]表示第i天持股(j = 1)/不持股(j = 0)时的最大利润
         状态转移：("188.买卖股票的最佳时机III"中，k=1的情况，推导后可以把k这个维度约掉)
         dp[i][0] = max(dp[i-1][0], dp[i-1][1] + price[i])
         dp[i][1] = max(dp[i-1][1], - price[i])
         */
        public int maxProfit(int[] prices) {
            // 定义并初始化状态
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];

            // 状态转移
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], - prices[i]);
            }
            return dp[n-1][0];  // 最后一天不持股一定比持股的利润高
        }


    }



}
