package data_structure_algorithm.leetcode;

public class _5longestPalindrome {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i][j]表示s[i,...,j]是否是回文串
         状态转移：len表示子串长度
         len = 1, dp[i][j] = true
         len = 2, dp[i][j] = s[i] == s[j]
         len > 2, dp[i][j] = dp[i+1][j-1] && s[i] == s[j]

         时间复杂度：O(N^2)
         空间复杂度：O(N^2)
         */
        public String longestPalindrome(String s) {
            // 特殊处理边界
            int n = s.length();
            if (n < 2) return s;

            // 定义状态
            boolean[][] dp = new boolean[n][n];

            // 初始状态
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;    // 长度为1时都是true
            }

            // 状态转移（枚举长度）
            int maxLen = 1; // 这里不可初始化为0
            int start = 0;
            for (int len = 2; len <= n; len++) {
                // i表示左边界
                for (int i = 0; i < n; i++) {
                    // j表示右边界
                    int j = i + len - 1;
                    if (j >= n) break;

                    // 更新状态
                    if (s.charAt(i) != s.charAt(j)) {
                        dp[i][j] = false;
                    } else {
                        if (len == 2) {
                            dp[i][j] = true;
                        } else {
                            dp[i][j] = dp[i+1][j-1];
                        }
                    }

                    // 更新最长长度及下标
                    if (dp[i][j] && len > maxLen) {
                        start = i;
                        maxLen = len;
                    }
                }
            }

            // 返回最长子串
            return s.substring(start, start + maxLen);
        }

    }



    public static class Solution2 {

        /**
         双指针：中心扩展
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public String longestPalindrome(String s) {
             String res = "";
             for (int i = 0; i < s.length(); i++) {
                 String s1 = isPalindrome(s, i, i);
                 String s2 = isPalindrome(s, i, i + 1);
                 res = res.length() < s1.length() ? s1 : res;
                 res = res.length() < s2.length() ? s2 : res;
             }
             return res;
         }

         /**
             判断以(left, right)为中心的子串是否是回文串
                 时间复杂度：O(N)
                 空间复杂度：O(1)
          */
         private String isPalindrome(String s, int left, int right) {
             while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                 left--;
                 right++;
             }
             return s.substring(left + 1, right);
         }

    }


}
