package interview.booking_OA_history;

import org.junit.Test;

public class FindLargestSquareSize {

    /**
     * 题目描述：有一个n*n大小的二维矩阵，元素是0或1，求全部由1组成的正方形的最大边长
     */

    public static class Solution1 {

        @Test
        public void test() {
            int[][] samples = new int[][] {{1, 1, 1, 1, 1}, {1, 1, 1, 0, 0}, {1, 1, 1, 0, 0}, {1, 1, 1, 0, 0}, {1, 1, 1, 1, 1}};
            int maxSize = findLargestSquareSize(samples);
            System.out.println(maxSize);
        }

        public int findLargestSquareSize(int[][] samples) {
            int n = samples.length, maxSize = 0;

            // 定义状态：dp[i][i]表示以samples[i - 1][j - 1]为右下角的最大正方形边长
            int[][] dp = new int[n + 1][n + 1];

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (samples[i - 1][j - 1] == 0) continue;
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    maxSize = Math.max(maxSize, dp[i][j]);
                }
            }
            return maxSize;
        }

    }

}
