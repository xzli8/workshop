package data_structure_algorithm.leetcode;

public class _10isMatch {

    public static class Solution1 {

        /**
         动态规划：https://leetcode.cn/problems/regular-expression-matching/solutions/296114/shou-hui-tu-jie-wo-tai-nan-liao-by-hyj8/
         定义状态：dp[i][j]表示s的前i个字符与p的前j个字符是否匹配（注意这里的i表示个数，从1开始，不是从0开始的下标）
         状态转移：
         如果能s[i]和p[j]相等，或者p[j]是万能字符'.'，那么其匹配结果取决于s[0...i-1]和p[0...j-1]的匹配结果
         if (s[i] == p[j] || p[j] == '.') dp[i][j] = dp[i-1][j-1]
         如果p[j]是'*'
         如果s[i]与p[j-1]不相等，也就是s[i]与p[j]('*')的前一个字符不匹配，就把p[j-1]p[j]这个模式丢弃，
         （也就是匹配0次）看其与p[j-2]的匹配情况
         if (s[i] != p[j-1]) dp[i][j] = dp[i][j-2]
         如果s[i]与p[j-1]相等，或者p[j-1]是万能字符'.'，那么要看如何与前面字符匹配
         if (s[i] == p[j-1] || p[j-1] == '.')
         dp[i][j] = dp[i][j-2] || dp[i-1][j-2] || dp[i-1][j]
         分别对应p[j-1]p[j]这个模式用0次、1次、多次
         时间复杂度：
         空间复杂度：
         */
        public boolean isMatch(String s, String p) {
            // 定义并初始化状态
            int n = s.length(), m = p.length();
            boolean[][] dp = new boolean[n+1][m+1];
            // 空的s和空的p可以匹配
            dp[0][0] = true;
            // 空的s和非空的p可能匹配，比如：a*
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j-1) == '*') dp[0][j] = dp[0][j-2];
            }
            // 非空的s和空的p不可能匹配，直接默认为false

            // 状态转移
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                        dp[i][j] = dp[i-1][j-1];
                    }
                    if (p.charAt(j-1) == '*') {
                        if (p.charAt(j-2) != s.charAt(i-1)) {
                            dp[i][j] = dp[i][j-2];
                        }
                        if (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                            dp[i][j] = dp[i][j-2] || dp[i-1][j-2] || dp[i-1][j];
                        }
                    }
                }
            }
            return dp[n][m];
        }

    }

}
