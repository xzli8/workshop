package data_structure_algorithm.algorithm.knapsack._02complete;

import org.junit.Test;

import java.util.Arrays;

public class MaxValue {

    /**
     * 问题定义：有一组物品，每个物品的数量为无限个，第i个物品的重量为weights[i]，价值为values[i]，有一个容量为capacity的背包，求背包能装下的最大价值
     */
    @Test
    public void testMaxValue() {
        int[] weights = new int[] {1, 2, 3, 4};
        int[] values = new int[] {2, 4, 4, 5};
        int capacity = 5;
        System.out.println(maxValue1(weights, values, capacity));
        System.out.println(maxValue2(weights, values, capacity));
        System.out.println(maxValue3(weights, values, capacity));
    }



    /**
     *  定义状态：dp[i][j]表示决策第i个物品后，背包的重量为j时的最大价值
     *  状态转移：dp[i][j] = max(dp[i][j], dp[i-1][j - k * weights[i]] + k * values[i]) k = [0, capacity / weights[i]]
     *  初始状态：dp[0][k * weights[0]] = k * values[0], k = [0, capacity / weights[0]]
     *
     *  时间复杂度：O(N * M * K)
     *  空间复杂度：O(N * M)
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
            for (int j = 0; j <= capacity; j++) {
                // 将上一层的结果copy过来
                dp[i][j] = dp[i - 1][j];

                // 放入k个第i个物品
                for (int k = 0; k * weights[i] <= j; k++) {
                    if (dp[i - 1][j - k * weights[i]] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * weights[i]] + k * values[i]);
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
            for (int j = 0; j <= capacity; j++) {   // 内循环正序/逆序遍历都可以
                for (int k = 0; k * weights[i] <= j; k++) {
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



    /**
     *  时间复杂度优化：O(M * N)
     */
    public int maxValue3(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        // 初始状态
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 状态转移
        for (int i = 0; i < n; i++) {
            // 注意：不同与01背包j一定要从大到小遍历，这里j一定要从小到大遍历
            // 01背包因为物品只有一件，是用上一层的状态计算这一层，
            // 完全背包因为物品数量无限，是用本层前面的状态计算本层后面的状态
            for (int j = 0; j <= capacity; j++) {
                if (j - weights[i] >= 0 && dp[j - weights[i]] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }

//        // 初始状态
//        Arrays.fill(dp, -1);
//        for (int k = 0; k * weights[0] <= capacity; k++) {
//            dp[k * weights[0]] = k * values[0];
//        }
//
//        // 状态转移
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j <= capacity; j++) {
//                if (j - weights[i] >= 0 && dp[j - weights[i]] >= 0) {
//                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
//                }
//            }
//        }

        // 返回最大价值
        int maxValue = 0;
        for (int j = 0; j <= capacity; j++) {
            maxValue = Math.max(maxValue, dp[j]);
        }
        return maxValue;
    }

}
