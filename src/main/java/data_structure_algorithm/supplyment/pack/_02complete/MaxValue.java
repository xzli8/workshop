package data_structure_algorithm.supplyment.pack._02complete;

import org.junit.Test;

import java.util.Arrays;

public class MaxValue {

    @Test
    public void testMaxValue() {
        int[] weights = new int[] {1, 2, 3, 4};
        int[] values = new int[] {2, 4, 4, 5};
        int capacity = 5;
        System.out.println(maxValue1(weights, values, capacity));
        System.out.println(maxValue2(weights, values, capacity));
    }



    /**
     *  问题定义：有一组物品，每个物品的数量为无限个，第i个物品的重量为weights[i]，价值为values[i]，有一个容量为capacity的背包，求背包能装下的最大价值
     *      定义状态：dp[i][j]表示决策第i个物品后，背包的重量为j时的最大价值
     *      状态转移：dp[i][j] = dp[i-1][j] || dp[i-1][j - k * weights[i]] + k * values[i], k = [0, capacity / weights[i]]
     *      初始状态：dp[0][k * weights[0]] = k * values[0], k = [0, capacity / weights[0]]
     *
     *      时间复杂度：O(N * M * K)
     *      空间复杂度：O(N * M)
     */
    public int maxValue1(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];

        // 初始状态
        Arrays.fill(dp[0], -1);
        for (int k = 0; k * weights[0] <= capacity; k++) {
            dp[0][k * weights[0]] = k * values[0];
        }

        // 状态转移
        for (int i = 1; i < n; i++) {

            // 先不放第i个物品，也就是先把i-1的状态copy一份过来；然后考虑是否放第i个物品，当能放下时，选择放或者不放的最大值
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品不放
                dp[i][j] = dp[i - 1][j];

                // 第i个物品能放下时，选择放或者不放的最大值
                for (int k = 0; j - k * weights[i] >= 0; k++) {
                    if (dp[i - 1][j - k * weights[i]] >= 0) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k * weights[i]] + k * values[i]);
                    }
                }
            }
        }

        // 返回最大价值（背包不必装满）
        int maxValue = -1;
        for (int j = 0; j <= capacity; j++) {
            maxValue = Math.max(maxValue, dp[n - 1][j]);
        }
        return maxValue;

        // 返回最大价值（背包必须装满）
//        return dp[n - 1][capacity];
    }


    /**
     *  空间压缩：状态数组从2D压缩为1D
     */
    public int maxValue2(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        // 初始状态
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int k = 0; k * weights[0] <= capacity; k++) {
            dp[k * weights[0]] = k * values[0];
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                for (int k = 0; j - k * weights[i] >= 0; k++) {
                    if (dp[j - k * weights[i]] >= 0) {
                        dp[j] = Math.max(dp[j], dp[j - k * weights[i]] + k * values[i]);
                    }
                }
            }
        }

        // 返回最大价值（背包不必装满）
        int maxValue = -1;
        for (int j = 0; j <= capacity; j++) {
            maxValue = Math.max(maxValue, dp[j]);
        }
        return maxValue;

        // 返回最大价值（背包必须装满）
//        return dp[capacity];
    }

}
