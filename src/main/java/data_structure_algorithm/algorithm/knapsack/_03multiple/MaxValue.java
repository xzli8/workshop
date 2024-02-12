package data_structure_algorithm.algorithm.knapsack._03multiple;

import org.junit.Test;

import java.util.Arrays;

public class MaxValue {

    /**
     *  问题描述：有一堆物品，重量和价值分别为weights[i]和values[i]，每种物品的最大可用数量分别为nums[i]，现在有一个容量为capacity的背包，问能装下的最大价值是多少？
     */
    @Test
    public void test() {
        int[] weights = new int[] {1, 3, 4};
        int[] values = new int[] {15, 20, 30};
        int[] nums = new int[] {2, 3, 2};
        int capacity = 10;
        System.out.println(maxValue1(weights, values, nums, capacity));
        System.out.println(maxValue2(weights, values, nums, capacity));
    }



    public int maxValue1(int[] weights, int[] values, int[] nums, int capacity) {
        // 定义状态
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];

        // 初始状态
        Arrays.fill(dp[0], -1);
        for (int k = 0; k <= nums[0]; k++) {
            if (k * weights[0] <= capacity) {
                dp[0][k * weights[0]] = k * values[0];
            }
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品不放
                dp[i][j] = dp[i - 1][j];

                // 第i个物品放
                for (int k = 0; k <= nums[i]; k++) {
                    if (j - k * weights[i] >= 0 && dp[i - 1][j - k * weights[i]] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * weights[i]] + k * values[i]);
                    }
                }
            }
        }

        // 返回最大价值(背包不必装满)
        int maxValue = -1;
        for (int j = 0; j <= capacity; j++) {
            maxValue = Math.max(maxValue, dp[n - 1][j]);
        }
        return maxValue;

        // 返回最大价值(背包必须装满)
//        return dp[n - 1][capacity];
    }

    /**
     *  空间压缩
     */
    public int maxValue2(int[] weights, int[] values, int[] nums, int capacity) {
        // 定义状态
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        // 初始状态
        Arrays.fill(dp, -1);
        for (int k = 0; k <= nums[0]; k++) {
            if (k * weights[0] <= capacity) {
                dp[k * weights[0]] = k * values[0];
            }
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {   // 内循环正序/逆序遍历都可以
                for (int k = 0; k <= nums[i]; k++) {
                    if (j - k * weights[i] >= 0 && dp[j - k * weights[i]] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                    }
                }
            }
        }

        // 返回最大价值（背包不必装满）
        int maxValue = -1;
        for (int j = 0; j <= capacity; j++) maxValue = Math.max(maxValue, dp[j]);
        return maxValue;

        // 返回最大价值（背包必须装满）
//        return dp[capacity];
    }


}
