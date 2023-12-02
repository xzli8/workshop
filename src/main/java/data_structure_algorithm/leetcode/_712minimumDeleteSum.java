package data_structure_algorithm.leetcode;

public class _712minimumDeleteSum {

    public static class Solution1 {

        /**
         动态规划(类似题："583.两个字符串的删除操作")
         定义状态：dp[i][j]表示使s1[0, i-1]和s2[0, j-1]相等所需删除字符的ASCII值的最小和
         状态转移：
         if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
         dp[i][j] = dp[i - 1][j - 1];
         } else {
         dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1))
         }
         初始状态：dp[0][0] = 0,
         dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1),
         dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1)

         时间复杂度：O(N1 * N2)
         空间复杂度：O(N1 * N2)
         */
        public int minimumDeleteSum(String s1, String s2) {
            // 定义状态
            int n1 = s1.length(), n2 = s2.length();
            int[][] dp = new int[n1 + 1][n2 + 1];

            // 初始状态
            dp[0][0] = 0;
            for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
            for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1);

            // 状态转移
            for (int i = 1; i <= n1; i++) {
                for (int j = 1; j <= n2; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                    }
                }
            }
            return dp[n1][n2];
        }

    }



    public static class Solution2 {


    }



    public static class Solution3 {


    }



    public static class Solution4 {


    }


}
