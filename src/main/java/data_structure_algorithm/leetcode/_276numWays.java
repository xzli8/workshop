package data_structure_algorithm.leetcode;

public class _276numWays {

    /**
     * lintcode:https://www.lintcode.com/problem/514/description
     */

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]遍历到第i个柱子时的染色方案数量
         状态转移：第三根柱子要么和第一根柱子颜色不同，要么和第二根柱子颜色不同(不能同时和第一根还有第二根柱子的颜色相同)
         dp[i] = (dp[i - 2] + dp[i - 1]) * (k - 1)
         初始状态：dp[0] = k, dp[1] = k * k

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int numWays(int n, int k) {
             if (n == 1) return k;

             // 定义状态
             int[] dp = new int[n];

             // 初始状态
             dp[0] = k;
             dp[1] = k * k;

             // 状态转移
             for (int i = 2; i < n; i++) {
                 dp[i] = (dp[i - 2] + dp[i - 1]) * (k - 1);
             }
             return dp[n - 1];
         }

    }



    public static class Solution2 {

        /**
         动态规划：dp只与dp[i-2]和dp[i-1]有关，可以用临时变量代替dp数组，降低空间复杂度为O(1)
         */
        public int numWays(int n, int k) {
            if (n == 1) return k;
            // 定义状态
            int dp0 = k, dp1 = k * k;
            for (int i = 2; i < n; i++) {
                int dp2 = (dp0 + dp1) * (k - 1);
                dp0 = dp1;
                dp1 = dp2;
            }
            return dp1;
        }

    }

}
