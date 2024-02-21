package data_structure_algorithm.leetcode;

public class _97isInterleave {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示s1的前i个字符和s2的前j个字符能构成s3的前i+j个字符
         状态转移：dp[i][j] = (dp[i-1][j] && s1[i] == s3[i+j-1]) || (dp[i][j-1] && s2[j] == s3[i+j-1])
         初始状态：dp[0][0] = true

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            if (m + n != s3.length()) return false;

            // 定义状态
            boolean[][] dp = new boolean[m+1][n+1];

            // 初始状态
            dp[0][0] = true;
            // 将短路逻辑写在if条件中，若不相符直接终止，后面的没必要看
            for (int i = 1; i <= m && s1.charAt(i-1) == s3.charAt(i-1); i++) dp[i][0] = true;
            for (int j = 1; j <= n && s2.charAt(j-1) == s3.charAt(j-1); j++) dp[0][j] = true;

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i+j-1))
                            || (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1));
                }
            }
            return dp[m][n];
        }

    }

}
