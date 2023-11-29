package data_structure_algorithm.leetcode;

public class _115numDistinct {

    /**
     * 与编辑距离相比要简单一些，只需要考虑删除，不需要考虑替换和增加
     */

    public static class Solution1 {

        /**
         动态规划：用哨兵简化逻辑
         定义状态：dp[i][j]表示s[0, i-1]和t[0, j-1]的最大匹配个数
         状态转移：
         当s[i-1] = t[j-1]时，可以选择用s[i-1]匹配t[j-1]，此时个数为dp[i-1][j-1]；
         也可以不用s[i-1]去匹配t[j-1]，此时个数为dp[i-1][j]。两种情况相加即可。
         当s[i-1] != t[j-1]时，只能不用s[i-1]匹配t[j-1]，此时个数为dp[i-1][j]
         所以有如下状态转移方程：
         if s[i-1] == t[j-1], dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
         else , dp[i][j] = dp[i-1][j]
         初始状态：dp[i][0] = 1, dp[0][j] = 0

         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int numDistinct(String s, String t) {
            // 定义状态
            int m = s.length(), n = t.length();
            int[][] dp = new int[m+1][n+1];

            // 初始状态
            for (int i = 0; i <= m; i++) {
                dp[i][0] = 1;
            }

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i-1) == t.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            return dp[m][n];
        }

    }



    public static class Solution2 {

    }



    public static class Solution3 {

    }



    public static class Solution4 {

    }



}
