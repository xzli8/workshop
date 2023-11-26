package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _62uniquePaths {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示到达第i行，第j列的路径数
         状态转移：dp[i][j] = dp[i-1][j] + dp[i][j-1]
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public int uniquePaths(int m, int n) {
             // 定义状态
             int[][] dp = new int[m][n];

             // 初始化状态
             Arrays.fill(dp[0], 1);
             for (int i = 0; i < m; i++) {
                 dp[i][0] = 1;
             }

            // 状态转移
             for (int i = 1; i < m; i++) {
                 for (int j = 1; j < n; j++) {
                     dp[i][j] = dp[i-1][j] + dp[i][j-1];
                 }
             }
             return dp[m-1][n-1];
         }

    }



    public static class Solution2 {

        /**
         空间优化：dp[i][j]只与dp[i-1][j]和dp[i][j-1]有关，可以将i的维度优化掉
         时间复杂度：O(M * N)
         空间复杂度：O(N)
         */
        public int uniquePaths(int m, int n) {
            // 定义状态
            int[] dp = new int[n];

            // 初始状态
            Arrays.fill(dp, 1);

            // 状态转移
            for (int i = 1; i < m; i++) {
                // 注意遍历顺序是从左到右（遍历顺序与状态转移方程有关）
                for (int j = 1; j < n; j++) {
                    // dp[j]相当于dp[i-1][j]，dp[j-1]相当于dp[i][j-1]
                    dp[j] += dp[j-1];
                }
            }
            return dp[n-1];
        }

    }

}
