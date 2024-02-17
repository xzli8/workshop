package data_structure_algorithm.leetcode;

public class _44isMatch {

    public static class Solution0 {

        /**
         动态规划
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public boolean isMatch(String s, String p) {
            // 定义状态：dp[i][j]表示s[i-1]和p[j-1]是否匹配
            int m = s.length(), n = p.length();
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 初始状态
            dp[0][0] = true;    // 空s和空p匹配
            for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*'; // 空s和非空p可能匹配
            // 非空s和空p不可能匹配，初始化为默认值false即可

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    if (p.charAt(j - 1) == '*') {
                        // '*'匹配空字符/任意字符
                        dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
            return dp[m][n];
        }

    }



    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示s[0...i]和p[0...j]是否匹配
         状态转移：
         初始状态：

         时间复杂度：O(M*N)
         空间复杂度：O(M*N)
         */
        public boolean isMatch(String s, String p) {
            int m = s.length(), n = p.length();
            boolean[][] dp = new boolean[m+1][n+1];

            dp[0][0] = true;    // 空s和空p匹配
            for (int i = 1; i <= m; i++) {
                dp[i][0] = false;   // s和空p一定不匹配
            }
            for (int j = 1; j <= n; j++) {
                dp[0][j] = dp[0][j-1] && p.charAt(j-1) == '*';    // 空s和p是否匹配
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    if (p.charAt(j-1) == '*') {
                        // dp[i][j-1]表示*匹配空字符，dp[i-1][j]表示*匹配任意字符
                        dp[i][j] = dp[i][j-1] || dp[i-1][j];
                    }
                }
            }
            return dp[m][n];
        }

    }

}
