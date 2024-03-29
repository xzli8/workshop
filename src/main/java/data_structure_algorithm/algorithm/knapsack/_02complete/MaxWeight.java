package data_structure_algorithm.algorithm.knapsack._02complete;

import org.junit.Test;

public class MaxWeight {

    /**
     * 问题定义：有一组物品，每个物品的数量为无限个，第i个物品的重量为weights[i]，有一个容量为capacity的背包，求背包能装下的最大重量
     */
    @Test
    public void testMaxWeight() {
        int[] weights = new int[] {4, 5};
        int capacity = 11;
        System.out.println(maxWeight1(weights, capacity));
        System.out.println(maxWeight2(weights, capacity));
        System.out.println(maxWeight3(weights, capacity));
    }

    /**
     *  定义状态：dp[i][j]表示决策第i个物品后，背包的重量是否为j
     *  状态转移：dp[i][j] = dp[i-1][j] || dp[i-1][j - k * weights[i]], k = [0, capacity / weights[i]]
     *  初始状态：dp[0][k * weights[0]] = true, k = [0, capacity / weights[0]]
     *
     *  时间复杂度：O(N * M * K)
     *  空间复杂度：O(N * M)
     */
    public int maxWeight1(int[] weights, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[][] dp = new boolean[n][capacity + 1];

        // 初始状态
        for (int k = 0; k * weights[0] <= capacity; k++) {
            dp[0][k * weights[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品不放（下面k=0的情况已经包含了，可以不写）
//                dp[i][j] = dp[i - 1][j];

                // 第i个物品放
                for (int k = 0; k * weights[i] <= j; k++) {
                    if (dp[i - 1][j - k * weights[i]]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        // 返回最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[n - 1][j]) {
                return j;
            }
        }
        return 0;
    }


    /**
     *  空间压缩：状态数组从2D转为1D
     */
    public int maxWeight2(int[] weights, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[] dp = new boolean[capacity + 1];

        // 初始状态
        for (int k = 0; k * weights[0] <= capacity; k++) {
            dp[k * weights[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            // 注意：不同与01背包j一定要从大到小遍历，这里j从小到大还是从大到小都可以
            // 因为01背包中物品数量只有一个，只能由上一层推当前层；而完全背包中物品数量无限，可以由当前层推当前层（小重量 -> 大重量）
            for (int j = 0; j <= capacity; j++) {
                for (int k = 0; k * weights[i] <= j; k++) {
                    if (dp[j - k * weights[i]]) {
                        dp[j] = true;
                    }
                }
            }
        }

        // 返回最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[j]) {
                return j;
            }
        }
        return 0;
    }

    /**
     *  时间复杂度优化：O(M * N)
     */
    public int maxWeight3(int[] weights, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[] dp = new boolean[capacity + 1];

        // 初始状态
        dp[0] = true;

        // 状态转移
        for (int i = 0; i < n; i++) {
            // 注意：不同与01背包j一定要从大到小遍历，这里j一定要从小到大遍历
            // 01背包因为物品只有一件，是用上一层的状态计算这一层，
            // 完全背包因为物品数量无限，是用本层前面的状态计算本层后面的状态
            for (int j = 0; j <= capacity; j++) {
                if (j - weights[i] >= 0 && dp[j - weights[i]]) {
                    dp[j] = true;
                }
            }
        }


//        // 初始状态
//        for (int k = 0; k * weights[0] <= capacity; k++) {
//            dp[k * weights[0]] = true;
//        }
//
//        // 状态转移
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j <= capacity; j++) {
//                if (j - weights[i] >= 0 && dp[j - weights[i]]) {
//                    dp[j] = true;
//                }
//            }
//        }


        // 返回最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[j]) {
                return j;
            }
        }
        return 0;
    }

}