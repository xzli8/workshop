package data_structure_algorithm.leetcode;

public class _639numDecodings {

    public static class Solution1 {

        /**
         DP
         T: O(N)
         S: O(N)
         */
         public int numDecodings(String s) {
             // 定义状态：dp[i]表示前i-1个字符的解码方法数
             int n = s.length(), MOD = (int) 1e9 + 7;
             long[] dp = new long[n + 1];

             // 初始状态：空串的解码方法数为1
             dp[0] = 1;

             // 状态转移：
             for (int i = 1; i <= n; i++) {
                 dp[i] = dp[i - 1] * check1Digit(s.charAt(i - 1)) % MOD;
                 if (i > 1) {
                     dp[i] = (dp[i] + dp[i - 2] * check2Digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
                 }
             }
             return (int) dp[n];
         }

         private int check1Digit(char c) {
             if (c == '0') return 0;
             return c == '*' ? 9 : 1;
         }

         private int check2Digits(char c2, char c1) {
             if (c2 == '*' && c1 == '*') return 15;  // **
             if (c2 == '*') return c1 <= '6' ? 2 : 1;    // *1, *2
             if (c1 == '*') {
                 if (c2 == '1') return 9;    // 1*
                 if (c2 == '2') return 6;    // 2*
                 return 0;   // 3*
             }
             return (c2 != '0' && (c2 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
         }

    }



    public static class Solution2 {

        /**
         DP: 空间压缩
         T: O(N)
         S: O(1)
         */
        public int numDecodings(String s) {
            int n = s.length(), MOD = (int) 1e9 + 7;
            long a = 0, b = 1, c = 0;   // a = dp[i - 2], b = dp[i - 1], c = dp[i]
            for (int i = 1; i <= n; i++) {
                c = b * check1Digit(s.charAt(i - 1)) % MOD;
                if (i > 1) {
                    c = (c + a * check2Digits(s.charAt(i - 2), s.charAt(i - 1))) % MOD;
                }
                a = b;
                b = c;
            }
            return (int) c;
        }

        private int check1Digit(char c) {
            if (c == '0') return 0;
            return c == '*' ? 9 : 1;
        }

        private int check2Digits(char c2, char c1) {
            if (c2 == '*' && c1 == '*') return 15;  // **
            if (c2 == '*') return c1 <= '6' ? 2 : 1;    // *1, *2
            if (c1 == '*') {
                if (c2 == '1') return 9;    // 1*
                if (c2 == '2') return 6;    // 2*
                return 0;   // 3*
            }
            return (c2 != '0' && (c2 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
        }

    }

}
