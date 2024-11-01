package data_structure_algorithm.leetcode;

public class _664strangePrinter {

    public static class Solution1 {

        /**
         DP
         TimeComplexity: O(N^3)
         SpaceComplexity: O(N^2)
         Reference:
         1.https://leetcode.cn/problems/strange-printer/solutions/792309/xin-shou-pian-cong-xiao-wen-ti-zai-dao-q-qifh/
         2.https://leetcode.cn/problems/strange-printer/solutions/792236/qi-guai-de-da-yin-ji-by-leetcode-solutio-ogbu/
         */
        public int strangePrinter(String s) {
            int n = s.length();
            int[][] dp = new int[n][n];
            // i,j的枚举顺序
            for (int i = n - 1; i >= 0; i--) {
                dp[i][i] = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(j) == s.charAt(i)) {
                        dp[i][j] = dp[i][j - 1];
                    } else {
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
