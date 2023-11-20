package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _322coinChange {

    /**
     动态规划
     定义状态：dp[i]表示兑换总金额为i时需要的最少硬币数量
     状态转移：dp[i] = dp[i-cj] + 1，其中cj表示第j个硬币的面值
     时间复杂度：O(M * N)
     空间复杂度：O(N)
     */
    public int coinChange(int[] coins, int amount) {
        // 定义并初始化状态数组
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;

        // 状态转移
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
