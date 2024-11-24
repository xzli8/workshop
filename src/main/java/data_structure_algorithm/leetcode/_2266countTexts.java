package data_structure_algorithm.leetcode;

public class _2266countTexts {

    public static class Solution1 {

        /**
         DP:
         T: O(N)
         S: O(N)
         */
        public int countTexts(String pressedKeys) {
            // 定义状态：dp[i]表示[0, i-1]子串代表的数目
            int n = pressedKeys.length(), MOD = (int) 1e9 + 7;
            int[] dp = new int[n + 1];

            // 初始状态：空串为1
            dp[0] = 1;

            // 状态转移：dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3] + dp[i - 4]
            for (int i = 1; i <= n; i++) {
                char c = pressedKeys.charAt(i - 1);
                for (int j = 1; j <= ((c == '7' || c == '9') ? 4 : 3) && i - j >= 0; j++) {
                    int k = i - j;
                    if (c != pressedKeys.charAt(k)) {
                        break;
                    }
                    dp[i] = (dp[i] + dp[k]) % MOD;
                }
            }
            return dp[n];
        }

    }

}
