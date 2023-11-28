package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _1143longestCommonSubsequence {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示text1[0...i]和text2[0...j]最长公共子序列
         状态转移：dp[i][j] = text1[i] == text2[j] ? dp[i-1][j-1] + 1 : max(dp[i-1][j], dp[i][j-1])
         初始状态：dp[i][0] = text1[i] == text2[0] ? 1 : 0, dp[0][j] = text2[j] == text1[i] ? 1 : 0

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int longestCommonSubsequence(String text1, String text2) {
            // 定义状态
            int n1 = text1.length(), n2 = text2.length();
            int[][] dp = new int[n1][n2];

            // 初始状态
            dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
            for (int i = 1; i < n1; i++) {
                if (text1.charAt(i) == text2.charAt(0)) {
                    dp[i][0] = dp[i - 1][0] + 1;
                } else {
                    dp[i][0] = dp[i - 1][0];
                }
            }
            for (int j = 1; j < n2; j++) {
                if (text2.charAt(j) == text1.charAt(0)) {
                    dp[0][j] = dp[0][j - 1] + 1;
                } else {
                    dp[0][j] = dp[0][j - 1];
                }
            }

            // 状态转移(i, j循环的先后次序无所谓)
            for (int i = 1; i < n1; i++) {
                for (int j = 1; j < n2; j++) {
                    if (text1.charAt(i) == text2.charAt(j)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[n1 - 1][n2 - 1];
        }

        @Test
        public void test() {
//            System.out.println(longestCommonSubsequence("abcde", "ace"));

            String text1 = "aa";
            String text2 = "aaa";
            System.out.println(longestCommonSubsequence(text1, text2));
            System.out.println(text1.length());
            System.out.println(text2.length());
        }

    }



    public static class Solution2 {

        @Test
        public void test() {
//            System.out.println(longestCommonSubsequence("abcde", "ace"));

            String text1 = "aa";
            String text2 = "aaa";
            System.out.println(longestCommonSubsequence(text1, text2));
        }



        /**
         哨兵简化初始化逻辑：状态数组多定义一行边界值，dp[i][j]表示text1[0, i-1]和text2[0, j-1]的最长公共子序列
         */
        public int longestCommonSubsequence(String text1, String text2) {
            if (text1 == null || text2 == null) return 0;
            int n1 = text1.length();
            int n2 = text2.length();
            if (n1 == 0 || n2 == 0) return 0;

            // 定义状态
            int[][] dp = new int[n1 + 1][n2 + 1];

            // 初始状态
            // 数组元素默认初始化为0，可省略

            // 状态转移
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (text1.charAt(i-1) == text2.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                    else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
            return dp[n1][n2];
        }

    }



    public static class Solution3 {

    }



    public static class Solution4 {

    }



}
