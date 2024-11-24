package data_structure_algorithm.leetcode;

public class _91numDecodings {

    public static class Solution1 {

        /**
         相同题：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/

         动态规划
         定义状态：dp[i]表示前i个字符s[1...i]的解码方法数
         状态转移：dp[i]可能由dp[i-1]或者dp[i-2]转换而来
         if s[i]属于[1, 9]，dp[i] = dp[i-1]
         if s[i-1]不等于0，并且s[i-1]s[i]属于[1,26]，dp[i] = dp[i-2]
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int numDecodings(String s) {
            // 定义并初始化状态
            int n = s.length();
            int[] dp = new int[n+1];
            dp[0] = 1;  // 空字符可以有一种解码方法

            // 状态转移
            for (int i = 1; i <= n; i++) {
                // 第i个字符在s中的下标是i-1
                if (s.charAt(i-1) != '0') {
                    dp[i] += dp[i-1];
                }
                if (i > 1 && s.charAt(i-2) != '0' && ((s.charAt(i-2) - '0') * 10 + (s.charAt(i-1) - '0') <= 26)) {
                    dp[i] += dp[i-2];
                }
            }
            return dp[n];
        }

    }



    public static class Solution2 {

        /**
         DP:压缩空间
         T: O(N)
         S: O(1)
         */
        public int numDecodings(String s) {
            int n = s.length(), a = 0, b = 1, c = 0;    // a = dp[i - 2], b = dp[i - 1], c = dp[i]
            for (int i = 1; i <= n;  i++) {
                c = b * check1Digit(s.charAt(i - 1));
                if (i > 1) {
                    c += a * check2Digits(s.charAt(i - 2), s.charAt(i - 1));
                }
                a = b;
                b = c;
            }
            return c;
        }

        private int check1Digit(char c) {
            return c == '0' ? 0 : 1;
        }

        private int check2Digits(char c2, char c1) {
            return (c2 != '0' && (c2 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
        }

    }



}
