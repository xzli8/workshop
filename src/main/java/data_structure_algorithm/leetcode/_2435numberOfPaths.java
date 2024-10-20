package data_structure_algorithm.leetcode;

public class _2435numberOfPaths {

    public static class Solution1 {

        /**
         DP
         */
        public int numberOfPaths(int[][] grid, int k) {
            // 定义状态：dp[i][j][r]表示到点[i, j]的所有路径中，被k整除余数为r(remainder)的路径数
            int mod = (int) 1e9 + 7, m = grid.length, n = grid[0].length;
            int[][][] dp = new int[m][n][k];

            // 初始状态
            dp[0][0][grid[0][0] % k] = 1;
            for (int i = 1; i < m; i++) {
                for (int r = 0; r < k; r++) {
                    dp[i][0][(r + grid[i][0]) % k] = dp[i - 1][0][r] % mod;
                }
            }
            for (int j = 1; j < n; j++) {
                for (int r = 0; r < k; r++) {
                    dp[0][j][(r + grid[0][j]) % k] = dp[0][j - 1][r] % mod;
                }
            }

            // 状态转移
            for(int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    for (int r = 0; r < k; r++) {
                        dp[i][j][(r + grid[i][j]) % k] = (dp[i - 1][j][r] + dp[i][j - 1][r]) % mod;
                    }
                }
            }
            return dp[m - 1][n - 1][0];
        }

    }

}
