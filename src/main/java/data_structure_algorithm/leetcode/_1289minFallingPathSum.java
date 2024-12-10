package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1289minFallingPathSum {

    public static class Solution1 {

        /**
         DP
         T: O(N^3)
         S: O(N^2)
         */
        public int minFallingPathSum(int[][] grid) {
            // Define and initialize state: dp[i][j] presents the number sum of point[i, j]
            int n = grid.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            for (int j = 0; j < n; j++) {
                dp[0][j] = grid[0][j];
            }

            // Transfer state
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (k == j) continue;
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + grid[i][j]);
                    }
                }
            }

            // Find result
            int minSum = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                minSum = Math.min(minSum, dp[n - 1][j]);
            }
            return minSum;
        }

    }

}
