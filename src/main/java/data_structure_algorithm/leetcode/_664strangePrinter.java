package data_structure_algorithm.leetcode;

public class _664strangePrinter {

    public static class Solution1 {

        /**
         DP: O(N^3), O(N^2)
         Note:
            1. 当区间的两边字符相同时(aba), 它的打印次数可以从它的更小一层的子区间的打印次数而来
            2. 当区间两边字符不同时(abab)，它的打印次数会取其子区间中的最优解，这一部分我们需要枚举所有的可能性
         Reference:
            1.https://leetcode.cn/problems/strange-printer/solutions/792309/xin-shou-pian-cong-xiao-wen-ti-zai-dao-q-qifh/
            2.https://leetcode.cn/problems/strange-printer/solutions/792236/qi-guai-de-da-yin-ji-by-leetcode-solutio-ogbu/
         */
        public int strangePrinter(String s) {
            int n = s.length();
            // 定义状态：dp[i][j]表示在区间s[i, j]所需要的最少打印次数
            int[][] dp = new int[n][n];

            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    // 当区间的两边字符相同时(aba), 它的打印次数可以从它的更小一层的子区间的打印次数而来
                    if (s.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    // 当区间两边字符不同时(abab)，它的打印次数会取其子区间中的最优解，这一部分我们需要枚举所有的可能性
                    else {
                        int min = Integer.MAX_VALUE;
                        for (int k = i; k < j; k++) {
                            min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                        }
                        dp[i][j] = min;
                    }
                }
            }
            return dp[0][n - 1];
        }

    }

}
