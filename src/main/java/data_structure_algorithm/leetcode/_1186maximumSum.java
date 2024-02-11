package data_structure_algorithm.leetcode;

public class _1186maximumSum {

    public static class Solution1 {

        /**
         动态规划(类似题："53.最大子数组和")
         定义状态：dp[i][k]表示以arr[i]结尾，删除k次的的非空子数组的最大和，其中k = 0或1
         状态转移：
         dp[i][0] = max(dp[i - 1][0], 0) + arr[i]
         dp[i][1] = max(dp[i - 1][1] + arr[i], dp[i - 1][0])
         初始状态：dp[0][0] = arr[0], dp[0][1] = 0

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int maximumSum(int[] arr) {
             // 定义状态
             int n = arr.length;
             int[][] dp = new int[n][2];

             // 初始状态
             dp[0][0] = arr[0];
             dp[0][1] = 0;

             // 状态转移
             int max = arr[0];
             for (int i = 1; i < n; i++) {
                 dp[i][0] = Math.max(dp[i - 1][0], 0) + arr[i];
                 dp[i][1] = Math.max(dp[i - 1][1] + arr[i], dp[i - 1][0]);
                 max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
             }
             return max;
         }

    }



    public static class Solution2 {

        // 优化空间复杂度：因为dp[i]只与dp[i-1]有关，所以可以用临时变量代替状态数组，将空间复杂度减小为O(1)
        public int maximumSum(int[] arr) {
            int n = arr.length, dp0 = arr[0], dp1 = 0, max = arr[0];
            for (int i = 1; i < n; i++) {
                dp1 = Math.max(dp1 + arr[i], dp0);
                dp0 = Math.max(dp0, 0) + arr[i];
                max = Math.max(max, Math.max(dp0, dp1));
            }
            return max;
        }

    }

}
