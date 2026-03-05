package data_structure_algorithm.leetcode;

public class _72minDistance {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示从word1(0, ..., i-1)到word2(0, ..., j-1)需要的最少操作次数
         状态转移：dp[i][j] = word1[i-1] == word2[j-1] ? dp[i-1][j-1] : min{dp[i-1][j-1], dp[i-1][j], dp[i][j-1]} + 1
                替换：dp[i-1][j-1]；插入：dp[i-1][j]；删除：dp[i][j-1]

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int minDistance(String word1, String word2) {
            int n1 = word1.length(), n2 = word2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];
            for (int i = 0; i <= n1; i++) dp[i][0] = i;
            for (int j = 0; j <= n2; j++) dp[0][j] = j;
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    dp[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] :
                            Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
            return dp[n1][n2];
        }

    }

}
