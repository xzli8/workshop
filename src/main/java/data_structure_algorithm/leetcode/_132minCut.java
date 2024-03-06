package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _132minCut {

    public static class Solution1 {

        /**
         动态规划：首先用DP计算子串是否是回文串，然后用DP计算最小分割次数
         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public int minCut(String s) {
            // 计算子串是否是回文串
            int n = s.length();
            boolean[][] isPalindrome = new boolean[n][n];   // isPalindrome[i][j]表示子串s[i, j]是否是回文串
            for (int i = 0; i < n; i++) Arrays.fill(isPalindrome[i], true);
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (j == i + 1) isPalindrome[i][j] = s.charAt(i) == s.charAt(j);
                    else isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }

            // 计算最小分割次数
            int[] dp = new int[n];  // dp[i]表示[0, i]这一段分割成全部都回文子串所需要的最小分割次数
            Arrays.fill(dp, Integer.MAX_VALUE);
            for (int i = 0; i < n; i++) {
                // s[0, i]是回文串，无需分割
                if (isPalindrome[0][i]) dp[i] = 0;
                else {
                    for (int j = 0; j < i; j++) {
                        if (isPalindrome[j + 1][i]) dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
            return dp[n - 1];
        }

    }

}
