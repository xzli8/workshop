package data_structure_algorithm.leetcode;

public class _647countSubstrings {

    public static class Solution1 {

        /**
         动态规划(类似题："5.最长回文子串")
         定义状态：dp[i][j]表示s[i,j]是否是回文子串
         状态转移：
         当j - i = 1时，dp[i][j] = s[i] == s[j]
         当j - i > 1时，dp[i][j] = s[i] == s[j] && dp[i + 1][j - 1]
         初始状态：dp[i][i] = true

         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public int countSubstrings(String s) {
            // 定义状态
            int n = s.length();
            boolean[][] dp = new boolean[n][n];

            // 初始状态
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            // 状态转移(遍历方向和范围根据状态转移方程确定)
            int count = n;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    if (j - i == 1) {
                        dp[i][j] = s.charAt(i) == s.charAt(j);
                    } else {
                        dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                    }
                    if (dp[i][j]) count++;
                }
            }
            return count;
        }

    }



    public static class Solution2 {

        /**
         动态规划（类似5-最长回文子串）
         定义状态：dp[i][j]表示[i,...,j]是否为回文串
         状态转移：len表示子串长度
         len == 1, dp[i][j] = true
         len == 2, dp[i][j] = s[i] == s[j]
         len > 2,  dp[i][j] = dp[i+1][j-1] && s[i] == s[j]

         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public int countSubstrings(String s) {
            // 定义并初始化状态数组
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }

            // 长度为1的都是回文串
            int count = n;
            // 从长度为2开始，进行状态转移
            for (int len = 2; len <= n; len++) {
                // i表示左边界
                for (int i = 0; i < n; i++) {
                    // j表示右边界
                    int j = i + len - 1;
                    if (j >= n) break;

                    if (s.charAt(i) == s.charAt(j)) {
                        if (len == 2) dp[i][j] = true;
                        else dp[i][j] = dp[i+1][j-1];
                    } else {
                        dp[i][j] = false;
                    }

                    if (dp[i][j]) count++;
                }
            }
            return count;
        }

    }

}
