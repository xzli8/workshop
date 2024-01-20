package data_structure_algorithm.leetcode;

public class _265minCostII {

    /**
     * lintcode:https://www.lintcode.com/problem/534
     * ref:https://zhuanlan.zhihu.com/p/593492075
     */

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示第i个房子刷成颜色j所需要的最少花费
         状态转移：dp[i][j] = Math.min(dp[i - 1][!j]) + costs[i][j]
         初始状态：dp[0][j] = costs[0][j]

         时间复杂度：O(N * K^2)
         空间复杂度：O(N * K)
         */
        public int minCostII(int[][] costs) {
            // 定义状态
            int n = costs.length, k = costs[0].length;
            int[][] dp = new int[n][k];

            // 初始状态
            for (int i = 0; i < k; i++) dp[0][i] = costs[0][i];

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int l = 0; l < k; l++) {
                        if (l == j) continue;
                        min = Math.min(min, dp[i - 1][l]);
                    }
                    dp[i][j] = min + costs[i][j];
                }
            }

            // 返回结果
            int res = dp[n - 1][0];
            for (int i = 1; i < k; i++) res = Math.min(res, dp[n - 1][i]);
            return res;
        }

    }



    public static class Solution2 {

        /**
         动态规划：优化空间复杂度为O(K)
         */
        public int minCostII(int[][] costs) {
            int n = costs.length;
            if (n == 0) return 0;

            int k = costs[0].length;
            int[] dp = new int[k], tmp = new int[k];
            for (int i = 0; i < k; i++) dp[i] = costs[0][i];

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    int min = Integer.MAX_VALUE;
                    for (int l = 0; l < k; l++) {
                        if (l == j) continue;
                        min = Math.min(min, dp[l]);
                    }
                    tmp[j] = min + costs[i][j];
                }
                for (int j = 0; j < k; j++) dp[j] = tmp[j];
            }

            int res = dp[0];
            for (int i = 1; i < k; i++) res = Math.min(res, dp[i]);
            return res;
        }

    }



    public static class Solution3 {
        // 时间复杂度为O(N * K)的做法

    }

}
