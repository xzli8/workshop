package data_structure_algorithm.leetcode;

public class _583minDistance {


    public static class Solution1 {

        /**
         动态规划(类似题："712.两个字符串的最小ASCII删除和")
         定义状态：dp[i][j]表示使得word1[0, i-1]和word2[0, j-1]相同所需的最小步数
         状态转移：dp[i][j] = word1[i-1] == word2[j-1]? dp[i-1][j-1] : min(dp[i-1][j], dp[i][j-1]) + 1
         初始状态：dp[i][0] = i, dp[j][0] = j

         时间复杂度：O(N1 * N2)
         空间复杂度：O(N1 * N2)
         */
        public int minDistance(String word1, String word2) {
            // 定义状态
            int n1 = word1.length(), n2 = word2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];

            // 初始状态
            for (int i = 0; i <= n1; i++) dp[i][0] = i;
            for (int j = 0; j <= n2; j++) dp[0][j] = j;

            // 状态转移
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    }
                }
            }
            return dp[n1][n2];
        }

    }



    public static class Solution2 {

        /**
         动态规划：转换为求最长公共子序列，然后用两个串的长度和减去最长公共子序列，即可得到最小步数
         类似题：1143.最长公共子序列
         */

    }



}
