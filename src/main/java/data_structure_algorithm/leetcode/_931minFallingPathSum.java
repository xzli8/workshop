package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _931minFallingPathSum {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示下标为matrix[i][j]下降路径最小和
         状态转移：dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1]) + matrix[i][j]
         初始状态：dp[0][j] = matrix[0][j]

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
         public int minFallingPathSum(int[][] matrix) {
             // 定义状态
             int n = matrix.length;
             int[][] dp = new int[n][n];

             // 初始状态
             for (int i = 0; i < n; i++) {
                 Arrays.fill(dp[i], Integer.MAX_VALUE);
             }
             for (int j = 0; j < n; j++) {
                 dp[0][j] = matrix[0][j];
             }

             // 状态转移
             for (int i = 1; i < n; i++) {
                 // 考虑到边界，首位元素单独计算
                 dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]) + matrix[i][0];
                 for (int j = 1; j < n - 1; j++) {
                     dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i - 1][j + 1])
                     + matrix[i][j];
                 }
                 dp[i][n - 1] = Math.min(dp[i - 1][n - 2], dp[i - 1][n - 1]) + matrix[i][n - 1];
             }

             // 返回最小值
             int minValue = Integer.MAX_VALUE;
             for (int j = 0; j < n; j++) {
                 minValue = Math.min(minValue, dp[n - 1][j]);
             }
             return minValue;
         }

    }



    public static class Solution2 {

        /**
         空间压缩：dp[i]只与dp[i-1]有关
         */
        public int minFallingPathSum(int[][] matrix) {
            // 定义状态
            int n = matrix.length;
            int[] dp = new int[n];

            // 初始状态
            for (int j = 0; j < n; j++) {
                dp[j] = matrix[0][j];
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                // 这里需要先把dp数组copy出来一份，因为计算后面的dp[j]的时候需要用到前面的dp[j]
                // 如果在原数组上进行，在计算后面的dp[j]的时候，前面的dp[j]已经被覆盖了
                // 这里偷懒直接使用了java的语法糖Arrays.copyOf，但更好的做法是在i循环外面分配好内存，避免多次分配。我猜想现在的java编译器会优化这部分，但不确定。
                int[] tmp = Arrays.copyOf(dp, n);
                dp[0] = Math.min(tmp[0], tmp[1]) + matrix[i][0];
                for (int j = 1; j < n - 1; j++) {
                    dp[j] = Math.min(Math.min(tmp[j - 1], tmp[j]), tmp[j + 1]) + matrix[i][j];
                }
                dp[n - 1] = Math.min(tmp[n - 2], tmp[n - 1]) + matrix[i][n - 1];
            }

            // 返回最小值
            int minValue = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                minValue = Math.min(minValue, dp[j]);
            }
            return minValue;
        }

    }

}
