package data_structure_algorithm.leetcode;

public class _516longestPalindromeSubseq {

    public static class Solution1 {

        /**
         动态规划(参考labuladong题解)
         定义状态：dp[i][j]表示s[i, ..., j]中的最长回文子序列
         状态转移：
         s[i] == s[j], dp[i][j] = dp[i+1][j-1] + 2
         s[i] != s[j], dp[i][j] = max(dp[i][j-1], dp[i+1][j]) s[i]和s[j]不能同时出现在回文子串中
         初始状态：dp[i][i] = 1

         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public int longestPalindromeSubseq(String s) {
            // 定义状态
            int n = s.length();
            int[][] dp = new int[n][n];

            // 初始状态
            for (int i = 0; i < n; i++) {
                dp[i][i] = 1;
            }

            // 状态转移
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[0][n-1];
        }

    }
}
