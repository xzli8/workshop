package data_structure_algorithm.leetcode;

public class _64minPathSum {

    public static class Solution1 {

        /**
         动态规划：
         定义状态：dp[i][j]表示走到第i行，第j列的最小数字和
         状态转移：dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public int minPathSum(int[][] grid) {
             // 定义状态
             int m = grid.length, n = grid[0].length;
             int[][] dp = new int[m][n];

             // 初始状态
             dp[0][0] = grid[0][0];
             for (int i = 1; i < m; i++) {
                 dp[i][0] = dp[i - 1][0] + grid[i][0];
             }
             for (int j = 1; j < n; j++) {
                 dp[0][j] = dp[0][j - 1] + grid[0][j];
             }

             // 状态转移
             for (int i = 1; i < m; i++) {
                 for (int j = 1; j < n; j++) {
                     dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                 }
             }
             return dp[m - 1][n - 1];
         }

    }



    public static class Solution2 {

        /**
         优化空间：用一维数组存储状态，逐行/列递推
         */
        public int minPathSum(int[][] grid) {
            // 定义状态
            int m = grid.length, n = grid[0].length;
            int[] dp = new int[n];

            // 初始状态
            dp[0] = grid[0][0];
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j - 1] + grid[0][j];
            }

            // 状态转移
            for (int i = 1; i < m; i++) {
                dp[0] = dp[0] + grid[i][0]; // 注意第一列，只能由上面递推过来
                for (int j = 1; j < n; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
            return dp[n - 1];
        }

    }

}
