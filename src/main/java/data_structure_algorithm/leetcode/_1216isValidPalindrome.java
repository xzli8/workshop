package data_structure_algorithm.leetcode;

public class _1216isValidPalindrome {

    public static class Solution1 {

        /**
         * 动态规划：O(N^2), O(N^2) （类似题：5.最长回文子串）
         * ref: https://leetcode.doocs.org/lc/1216/
         */
        public boolean isValidPalindrome(String s, int k) {
            int n = s.length();
            int[][] f = new int[n][n];
            for (int i = 0; i < n; ++i) {
                f[i][i] = 1;
            }
            for (int i = n - 2; i >= 0; --i) {
                for (int j = i + 1; j < n; ++j) {
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = f[i + 1][j - 1] + 2;
                    } else {
                        f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                    }
                    if (f[i][j] + k >= n) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

}
