package data_structure_algorithm.leetcode;

public class _1312minInsertions {

    public static class Solution1 {

        /**
         动态规划：
         定义状态：dp[i][j]表示s的子串s[i, j]成为回文串的最小插入次数
         状态转移：dp[i][j] = s[i] == s[j] ? dp[i+1][j-1] : min(dp[i+1][j], dp[i][j-1]) + 1
         初始状态：dp[i][i] = 0

         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public int minInsertions(String s) {
            // 定义状态
            int n = s.length();
            int[][] dp = new int[n][n];

            // 初始状态
            for (int i = 0; i < n; i++) {
                dp[i][i] = 0;
            }

            // 状态转移(注意i和j的遍历范围和方向，根据状态转移方程确定遍历方向，根据题意确定遍历范围(i <= j))
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] : Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                }
            }
            return dp[0][n-1];
        }

    }

}
