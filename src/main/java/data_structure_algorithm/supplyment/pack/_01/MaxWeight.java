package data_structure_algorithm.supplyment.pack._01;

import org.junit.Test;

public class MaxWeight {

    /**
     * 有一组物品，重量是weights，现有一容量为capacity的背包，计算出背包能装下物品的最大重量，不要求背包必须装满
     */
    @Test
    public void test() {
        int[] weights = new int[] {2, 2, 4, 6, 3};
        int capacity = 16;

        System.out.println(maxWeight0(weights, capacity));
        System.out.println(maxWeight1(weights, capacity));
        System.out.println(maxWeight2(weights, capacity));
    }

    /**
     * 暴力枚举（带备忘录）：n个物品的决策分为n个阶段，对于每个物品，可以选择放入或不放入背包中
     *      时间复杂度：O(N)
     *      空间复杂度：O(N)
     */
    public int maxWeight0(int[] weights, int capacity) {
        visited = new boolean[weights.length][capacity + 1];
        backtrace(weights, capacity, 0, 0);
        return maxWeight;
    }

    private int maxWeight = 0;

    private boolean[][] visited;    // 这里其实就对应的动态规划中的状态

    public void backtrace(int[] weights, int capacity, int i, int cw) {
        if (i == weights.length || cw >= capacity) {
            maxWeight = Math.max(maxWeight, cw);
            return;
        }

        if (visited[i][cw]) return;
        visited[i][cw] = true;

        // 第i个物品不放入背包
        backtrace(weights, capacity, i + 1, cw);

        // 第i个物品放入背包（先判断能否放入）
        if (cw + weights[i] <= capacity) {
            backtrace(weights, capacity, i + 1, cw + weights[i]);
        }
    }



    /**
     *  动态规划
     *      定义状态：dp[i][j]表示第i个物品决策后，背包重量是否为j（对应回溯中[i][j]这个状态是否被访问过）
     *      状态转移：dp[i][j] = dp[i-1][j] || dp[i-1][j-weights[i]]
     *      初始状态：dp[0][0] = true, dp[0][weights[0]] = weights[0] <= capacity
     *
     *      时间复杂度：O(N*M)
     *      空间复杂度：O(N*M)
     */
    public int maxWeight1(int[] weights, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[][] dp = new boolean[n][capacity + 1];

        // 初始状态
        dp[0][0] = true;
        if (weights[0] <= capacity) {
            dp[0][weights[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {

//            // 第i个物品不放
//            for (int j = 0; j <= capacity; j++) {
//                dp[i][j] = dp[i-1][j];
//            }
//            // 第i个物品放
//            for (int j = 0; j <= capacity; j++) {
//                if (j + weights[i] <= capacity) {
//                    dp[i][j + weights[i]] = dp[i-1][j];
//                }
//            }

//            // 当第i个物品能放下时，可以选择不放或者放第i个物品；当第i个物品放不下时，只能不放
//            for (int j = 0; j <= capacity; j++) {
//                if (j - weights[i] >= 0) {
//                    dp[i][j] = dp[i-1][j] || dp[i-1][j-weights[i]];
//                } else {
//                    dp[i][j] = dp[i-1][j];
//                }
//            }

            // 先不放第i个物品，也就是先把i-1的状态copy一份过来；然后考虑是否放第i个物品，当能放下时放
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品不放
                dp[i][j] = dp[i - 1][j];

                // 第i个物品放
                if (j - weights[i] >= 0 && dp[i - 1][j - weights[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        // 计算最大重量
        for (int j = capacity; j >= 0; j--) {
            if (dp[n-1][j]) {
                return j;
            }
        }
        return 0;
    }



    /**
     *  动态规划(空间压缩)：dp[i][j]只与dp[i-1][j]有关，可以将i这一维压缩
     *      定义状态：dp[j]表示背包中的重量是否为j
     *      状态转移：dp[j] = dp[j - weight[i]]
     *      初始状态：dp[0] = true, dp[weights[0]] = weights[0] <= capacity
     *
     *      时间复杂度：O(N*M)
     *      空间复杂度：O(M)
     */
    public int maxWeight2(int[] weights, int capacity) {
        // 定义状态
        int n = weights.length;
        boolean[] dp = new boolean[capacity + 1];

        // 初始状态
        dp[0] = true;
        if (weights[0] <= capacity) {
            dp[weights[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            // 这里不能从前往后遍历，因为上层的临时结果还未被访问就被覆盖。
            // 这里因为是要用dp[j-weights[i]]计算dp[j]，即用前面的结果计算后面的结果
            for (int j = capacity; j >= 0; j--) {
                // 第i个物品不放，dp[j] = dp[j]，省略

                // 第i个物品放
//                if (j - weights[i] >= 0) {
//                    dp[j] = dp[j - weights[i]];
//                }

                // 第i个物品放
                if (j - weights[i] >= 0 && dp[j - weights[i]]) {
                    dp[j] = true;
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

}
