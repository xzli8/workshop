package data_structure_algorithm.leetcode;

public class _522findLUSlength {

    public static class Solution1 {

        /**
         LCS应用
         时间复杂度：O(M^2 * N^2)
         空间复杂度：O(M * N)
         */
        public int findLUSlength(String[] strs) {
            int n = strs.length, res = -1;

            // 枚举每个strs[i]，检查其是否为其他strs[j]的子序列
            for (int i = 0; i < n; i++) {
                // 剪枝
                if (strs[i].length() <= res) continue;

                // 判断strs[i]是否是特殊序列
                boolean isUS = true;
                for (int j = 0; j < n && isUS; j++) {
                    if (i == j) continue;
                    if (check(strs[i], strs[j])) isUS = false;
                }
                if (isUS) res = strs[i].length();
            }
            return res;
        }

        //
        /**
         检查s1是否为s2的子序列：转换为求s1和s2的最长公共子序列长度
         */
        private boolean check(String s1, String s2) {
            int n1 = s1.length(), n2 = s2.length();
            if (n1 > n2) return false;
            int[][] dp = new int[n1 + 1][n2 + 1];
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[n1][n2] == n1;
        }

    }

}
