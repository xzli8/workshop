package data_structure_algorithm.leetcode;

public class _256minCost {

    /**
     * leetcode:https://leetcode.cn/problems/JEj789/description/
     * lintcode:https://www.lintcode.com/problem/515
     */

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示第i个房子刷成颜色j所需要的最少花费
         状态转移：dp[i][j] = Math.min(dp[i - 1][!j]) + costs[i][j]
         初始状态：dp[0][j] = costs[0][j]

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int minCost(int[][] costs) {
             // 定义状态
             int n = costs.length;
             int[][] dp = new int[n][3];

             // 初始状态
             dp[0] = costs[0];

             // 状态转移
             for (int i = 1; i < n; i++) {
                 dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
                 dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
                 dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
             }
             return Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
         }

    }



    public static class Solution2 {

        /**
         动态规划：用临时变量替代数组，空间复杂度降低为O(1)
         */
        public int minCost(int[][] costs) {
            int n = costs.length, dp0 = costs[0][0], dp1 = costs[0][1], dp2 = costs[0][2];
            for (int i = 1; i < n; i++) {
                int tmp0 = Math.min(dp1, dp2) + costs[i][0];
                int tmp1 = Math.min(dp0, dp2) + costs[i][1];
                int tmp2 = Math.min(dp0, dp1) + costs[i][2];
                dp0 = tmp0;
                dp1 = tmp1;
                dp2 = tmp2;
            }
            return Math.min(Math.min(dp0, dp1), dp2);
        }

    }



    public static class Solution3 {

        // 动态规划：优化空间复杂度到O(1)
        public int minCost(int[][] costs) {
            // 边界情况
            int n = costs.length;
            if (n == 0) return 0;

            // 定义状态
            int[] dp = new int[3], tmp = new int[3];

            // 初始状态
            for (int i = 0; i < 3; i++) dp[i] = costs[0][i];

            // 状态转移
            for (int i = 1; i < n; i++) {
                tmp[0] = Math.min(dp[1], dp[2]) + costs[i][0];
                tmp[1] = Math.min(dp[0], dp[2]) + costs[i][1];
                tmp[2] = Math.min(dp[0], dp[1]) + costs[i][2];
                dp[0] = tmp[0];
                dp[1] = tmp[1];
                dp[2] = tmp[2];
            }
            return Math.min(Math.min(dp[0], dp[1]), dp[2]);
        }

    }

}
