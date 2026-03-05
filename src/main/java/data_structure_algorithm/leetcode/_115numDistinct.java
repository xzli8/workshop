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
            for (int i = 0; i <= m; i++) dp[i][0] = 1;

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

        // DFS + Memo
        int n, m;
        String target, source;
        Integer[][] memo;

        int countOriginal(String target, String source) {
            this.target = target; this.source = source;
            n = target.length(); m = source.length();
            memo = new Integer[n + 1][m + 1];
            return g(0, 0);
        }

        // 用 target[i:] 匹配 source[j:] 的方案数
        int g(int i, int j) {
            if (j == m) return 1;            // source 配完
            if (i == n) return 0;            // target 用完还没配完
            if (memo[i][j] != null) return memo[i][j];

            int res = g(i + 1, j);                       // 跳过 target[i]
            if (target.charAt(i) == source.charAt(j))
                res += g(i + 1, j + 1);                  // 选中 target[i],下一个从 i+1 起

            return memo[i][j] = res;
        }

        // 变体：不能用相邻的元素匹配
        int f(int i, int j) {
            if (j == m) return 1;
            if (i >= n) return 0;            // 注意用 >=,因为下面会跳到 i+2,可能等于 n+1
            if (memo[i][j] != null) return memo[i][j];

            int res = f(i + 1, j);                       // 跳过 target[i]
            if (target.charAt(i) == source.charAt(j))
                res += f(i + 2, j + 1);                  // 选中 target[i],下一个从 i+2 起 ← 唯一改动

            return memo[i][j] = res;
        }



        // 原题：DP
        int countOriginalDP(String target, String source) {
            int n = target.length(), m = source.length();
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) dp[i][m] = 1;   // source 配完 → 1 种
            // dp[n][j<m] 默认 0:target 用完没配完

            for (int i = n - 1; i >= 0; i--)
                for (int j = m - 1; j >= 0; j--) {
                    dp[i][j] = dp[i + 1][j];                         // 跳过
                    if (target.charAt(i) == source.charAt(j))
                        dp[i][j] += dp[i + 1][j + 1];                // 选中,推进 1
                }
            return dp[0][0];
        }

        // 变体：DP
        int countVariantDP(String target, String source) {
            int n = target.length(), m = source.length();
            int[][] dp = new int[n + 2][m + 1];                      // ← 多开一行
            for (int i = 0; i <= n + 1; i++) dp[i][m] = 1;
            // dp[n][j<m]、dp[n+1][j<m] 默认 0

            for (int i = n - 1; i >= 0; i--)
                for (int j = m - 1; j >= 0; j--) {
                    dp[i][j] = dp[i + 1][j];                         // 跳过
                    if (target.charAt(i) == source.charAt(j))
                        dp[i][j] += dp[i + 2][j + 1];                // 选中,推进 2 ← 唯一改动
                }
            return dp[0][0];
        }

    }

}
