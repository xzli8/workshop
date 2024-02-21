package data_structure_algorithm.leetcode;

public class _221maximalSquare {

    public static class Solution1 {

        /**
         动态规划
         ref：https://leetcode.cn/problems/maximal-square/solutions/44586/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/?envType=study-plan-v2&envId=top-interview-150
         定义状态：dp[i+1][j+1]表示以matrix[i][j]为右下角的最大正方形的边长
         状态转移：matrix[i][j] == 1时，dp[i][j] = max(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] dp = new int[m+1][n+1];
            int max = 0;
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (matrix[i-1][j-1] == '1') {
                        dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max * max;
        }

    }

}
