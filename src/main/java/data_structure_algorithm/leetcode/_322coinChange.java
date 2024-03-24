package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class _322coinChange {

    public static class Solution1 {

        /**
         动态规划：完全背包求最值(二维数组写法)
         时间复杂度：O(N * M * K)
         空间复杂度：O(N * M)
         */
        public int coinChange(int[] coins, int amount) {
            // 定义状态：dp[i][j]表示考虑到第i个硬币时，凑成金额为j的最少硬币个数
            int n = coins.length;
            int[][] dp = new int[n][amount + 1];

            // 初始状态
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], amount + 1);
            dp[0][0] = 0;
            for (int k = 0; k * coins[0] <= amount; k++) dp[0][k * coins[0]] = k;

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = 0; j <= amount; j++) {
                    dp[i][j] = dp[i - 1][j];
                    for (int k = 0; j - k * coins[i] >= 0; k++) {
                        if (dp[i - 1][j - k * coins[i]] <= amount) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - k * coins[i]] + k);
                        }
                    }
                }
            }
            return dp[n - 1][amount] == amount + 1 ? -1 : dp[n - 1][amount];
        }

    }



    public static class Solution2 {

        /**
         动态规划：完全背包求最值问题(一维数组写法：物品和背包的遍历顺序无所谓，内循环正序遍历)
         时间复杂度：O(N*M)
         空间复杂度：O(N)
         */
         public int coinChange(int[] coins, int amount) {
             // 定义状态：dp[i]表示凑成金额为i的最少硬币个数
             int[] dp = new int[amount + 1];

             // 初始状态
             Arrays.fill(dp, amount + 1);
             dp[0] = 0;

             // 状态转移
             for (int i = 1; i <= amount; i++) {
                 for (int j = 0; j < coins.length; j++) {
                     if (i - coins[j] >= 0) {
                         dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                     }
                 }
             }
             return dp[amount] == (amount + 1) ? -1 : dp[amount];
         }

    }

}
