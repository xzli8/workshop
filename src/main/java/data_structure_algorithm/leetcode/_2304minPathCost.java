package data_structure_algorithm.leetcode;

public class _2304minPathCost {

    public static class Solution1 {

        /**
         DP
         T: O(M * N^2)
         S: O(M * N)
         */
        public int minPathCost(int[][] grid, int[][] moveCost) {
            // Define state
            int m = grid.length, n = grid[0].length;
            int[][] dp = new int[m][n];

            // Initialize state
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Integer.MAX_VALUE;
                }
            }
            for (int j = 0; j < n; j++) {
                dp[0][j] = grid[0][j];
            }

            // Transfer state
            int res = Integer.MAX_VALUE;
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + moveCost[grid[i - 1][k]][j] + grid[i][j]);
                    }
                    if (i == m - 1) res = Math.min(res, dp[i][j]);
                }
            }
            return res;
        }

    }

}
