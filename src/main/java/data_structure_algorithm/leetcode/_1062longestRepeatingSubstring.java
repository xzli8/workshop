package data_structure_algorithm.leetcode;

public class _1062longestRepeatingSubstring {

    /**
     * https://leetcode.doocs.org/lc/1062/
     */

    public static class Solution1 {

        /**
         * DP: O(N^2), O(N^2)
         *  状态定义：dp[i][j]表示以s[i], s[j]结尾的最长重复子串的长度
         *  状态转移：if (s[i] == s[j]) dp[i][j] = dp[i - 1][j - 1] + 1 else dp[i][j] = 0
         */
        public int longestRepeatingSubstring(String s) {
            int n = s.length(), max = 0;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) dp[i][0] = 0;
            for (int j = 0; j < n; j++) dp[0][j] = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max;
        }

        /**
         * DP: O(N^2), O(N^2)
         *  定义状态：dp[i][j]表示s[i - 1]和s[j - 1]结尾的最长重复子串的长度
         *  状态转移：if (s[i - 1] == s[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1 else dp[i][j] = 0
         */
        public int longestRepeatingSubstringII(String s) {
            int n = s.length(), max = 0;
            int[][] dp = new int[n + 1][n + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        max = Math.max(max, dp[i][j]);
                    }
                }
            }
            return max;
        }

    }

}
