package data_structure_algorithm.algorithm.knapsack._03multiple;

import org.junit.Test;

public class MaxWeight {

    /**
     * 问题定义：有一组物品，每个物品的数量为nums[i]个，第i个物品的重量为weights[i]，有一个容量为capacity的背包，求背包能装下的最大重量
     */
    @Test
    public void testMaxWeight() {
        int[] weights = new int[] {4, 5};
        int[] nums = new int[] {2, 3, 2};
        int capacity = 11;
        System.out.println(maxWeight1(weights, nums, capacity));
        System.out.println(maxWeight2(weights, nums, capacity));
    }



    public int maxWeight1(int[] weights, int[] nums, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[][] dp = new boolean[n][capacity + 1];

        // 初始状态
        for (int k = 0; k <= nums[0]; k++) {
            if (k * weights[0] <= capacity) {
                dp[0][k * weights[0]] = true;
            }
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                for (int k = 0; k <= nums[i]; k++) {
                    if (k * weights[i] <= j && dp[i - 1][j - k * weights[i]]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        // 计算最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[n - 1][j]) return j;
        }
        return 0;
    }



    public int maxWeight2(int[] weights, int[] nums, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[] dp = new boolean[capacity + 1];

        // 初始状态
        for (int k = 0; k <= nums[0]; k++) {
            if (k * weights[0] <= capacity) {
                dp[k * weights[0]] = true;
            }
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                for (int k = 0; k <= nums[i]; k++) {
                    if (k * weights[i] <= j && dp[j - k * weights[i]]) {
                        dp[j] = true;
                    }
                }
            }
        }

        // 返回最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[j]) return j;
        }
        return 0;
    }



}
