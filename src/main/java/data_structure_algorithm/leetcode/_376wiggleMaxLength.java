package data_structure_algorithm.leetcode;

public class _376wiggleMaxLength {

    public static class Solution1 {

        /**
         动态规划(类似题："122-买卖股票的最佳时间II")
         定义状态：
         dp[i][0]表示以第i个数结尾并且为“谷”时的最长摆动序列的长度
         dp[i][1]表示以第i个数结尾并且为“峰”时的最长摆动序列的长度
         状态转移：
         dp[i][0] = nums[i] < nums[j] ? max(dp[i][0], dp[j][1] + 1)
         dp[i][1] = nums[i] > nums[j] ? max(dp[i][1], dp[j][0] + 1)

         如果不是"序列"，是"数组"，则有如下状态转移方程：(序列不连续，数组连续)
         dp[i][0] = nums[i] < nums[i-1] ? dp[i-1][1] + 1 : 0;
         dp[i][1] = nums[i] > nums[i-1] ? dp[i-1][0] + 1 : 0;
         初始状态：一个元素时的最长摆动序列长度为1
         dp[i][0] = 1, dp[i][1] = 1

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int wiggleMaxLength(int[] nums) {
            // 定义状态
            int n = nums.length;
            int[][] dp = new int[n][2];

            // 初始状态
            for (int i = 0; i < n; i++) {
                dp[i][0] = 1;
                dp[i][1] = 1;
            }

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] < nums[j]) {
                        dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                    }
                    if (nums[i] > nums[j]) {
                        dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                    }
                }
            }
            return Math.max(dp[n - 1][0], dp[n - 1][1]);
        }

    }



    public static class Solution2 {

        /**
         贪心
         */


    }



}
