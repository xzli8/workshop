package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _265minCostII {

    /**
     * ref:
     *      https://zhuanlan.zhihu.com/p/593492075
     *      https://www.cnblogs.com/grandyang/p/5322870.html
     *      https://www.cnblogs.com/lightwindy/p/8476983.html
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals(5, minCostII(new int[][] {{1,5,3}, {2,9,4}}));
        }

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
            dp[0] = costs[0];

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

    }

}
