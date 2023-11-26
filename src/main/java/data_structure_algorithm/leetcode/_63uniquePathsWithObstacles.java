package data_structure_algorithm.leetcode;

public class _63uniquePathsWithObstacles {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示到达第i行第j列的路径数
         状态转移：dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i-1][j] + dp[i][j-1] : 0
         初始状态：dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0
         dp[i][0] = obstacleGrid[i][0] == 0 ? dp[i-1][0] : 0
         dp[0][j] = obstacleGrid[0][j] == 0 ? dp[0][j-1] : 0

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public int uniquePathsWithObstacles(int[][] obstacleGrid) {
             // 定义状态
             int m = obstacleGrid.length, n = obstacleGrid[0].length;
             int[][] dp = new int[m][n];
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     dp[i][j] = 0;
                 }
             }

             // 初始化状态
             dp[0][0] = obstacleGrid[0][0] == 0 ? 1 : 0;
             for (int i = 1; i < m; i++) {
                 dp[i][0] = obstacleGrid[i][0] == 0 ? dp[i-1][0] : 0;
             }
             for (int j = 1; j < n; j++) {
                 dp[0][j] = obstacleGrid[0][j] == 0 ? dp[0][j-1] : 0;
             }

             // 状态转移
             for (int i = 1; i < m; i++) {
                 for (int j = 1; j < n; j++) {
                     dp[i][j] = obstacleGrid[i][j] == 0 ? dp[i-1][j] + dp[i][j-1] : 0;
                 }
             }
             return dp[m-1][n-1];
         }

    }



    public static class Solution2 {

        /**
         空间优化：因为dp[i][j]只与dp[i-1][j]和dp[i][j-1]有关，可以将维度i优化掉
         时间复杂度：O(M * N)
         空间复杂度：O(N)
         */
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            // 定义状态
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[] dp = new int[n];

            // 初始状态
            for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
                dp[j] = 1;
            }

            // 状态转移(注意第一列元素，只能由上面的元素计算，即dp[j] = dp[j])
            for (int i = 1; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (obstacleGrid[i][j] == 1) {
                        dp[j] = 0;
                    } else if (j != 0) {
                        dp[j] += dp[j - 1];
                    }
                }
            }
            return dp[n-1];
        }

    }

}
