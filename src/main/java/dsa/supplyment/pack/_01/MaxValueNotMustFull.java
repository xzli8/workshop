package dsa.supplyment.pack._01;

import org.junit.Test;

import java.util.Arrays;

public class MaxValueNotMustFull {

    /**
     * 有一组物品，重量是weights，价值是values，现有一容量为capacity的背包，计算出背包能装下的物品的最大价值，不要求背包必须装满
     */
    @Test
    public void testNotMustFull() {
        int[] weights = new int[] {2, 2, 4, 6, 3};
        int[] values = new int[] {3, 4, 8, 9, 6};
        int capacity = 16;
        System.out.println(maxValueNotMustFull0(weights, values, capacity));
        System.out.println(maxValueNotMustFull1(weights, values, capacity));
        System.out.println(maxValueNotMustFull(weights, values, capacity));
        System.out.println(maxValueNotMustFullV2(weights, values, capacity));
    }



    /**
     * 回溯（分别考察每个物品，每个物品都有两个选择，装或者不装）
     *      也可以使用备忘录（用i和weight作为key，value作为value，用map存储，相同key取较大的value）
     *      但使用备忘录不一定快（因为不能保证每次都先遍历到value最大的路径）
     *
     *      时间复杂度：指数
     *      空间复杂度：O(N)
     */
    public int maxValueNotMustFull0(int[] weights, int[] values, int capacity) {
        maxValue = Integer.MIN_VALUE;
        backtrace(weights, values, capacity, 0, 0, 0);
        return maxValue;
    }

    private int maxValue;

    private void backtrace(int[] weights, int[] values, int capacity, int i, int weight, int value) {
        if (i == weights.length || weight == capacity) {
            maxValue = Math.max(maxValue, value);
            return;
        }

        // 不放第i个物品
        backtrace(weights, values, capacity, i + 1, weight, value);
        // 当第i个物品能放下时，放第i个物品
        if (weight + weights[i] <= capacity) {
            backtrace(weights, values, capacity, i + 1, weight + weights[i], value + values[i]);
        }
    }



    /**
     *  动态规划
     *      定义状态：dp[i][j]表示第i个物品决策后，背包重量为j时的最大价值
     *      状态转移：如果第i个物品不能放入背包，只能选择第i-1个物品的结果；如果能放入，需要选择放或者不放的最大值
     *          if (第i个物品不能放入) dp[i][j] = dp[i-1][j]
     *          else dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + values[i]
     *      初始状态：dp[0][0] = 0, dp[0][weights[0]] = weights[0] <= capacity ? values[0] : 0;
     */
    public int maxValueNotMustFull1(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];

        // 初始状态
        dp[0][0] = 0;
        if (weights[0] <= capacity) {
            dp[0][weights[0]] = values[0];
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品能放下，选择放或者不放的最大值
                if (j - weights[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
                // 第i个物品不能放下，只能取第i-1个物品的结果
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n-1][capacity];
    }

    /**
     *      动态规划
     *          定义状态：dp[i][j]表示对于前i件物品，背包当前重量为j时的最大价值（第i个物品的下标为i-1）
     *          状态转移：
     *              // 如果第i个物品不能放入背包，只能选择第i-1个物品的结果；如果能放入，需要选择放或者不放的最大值
     *              if (第i个物品不能放入背包) dp[i][j] = dp[i-1][j]
     *              else dp[i][j] = max(dp[i-1][j], dp[i-1][j-weights[i-1]]+values[i-1])
     *          初始状态：
     *              dp[0][j] = 0, 一件物品都不放时，价值为0
     */
    public int maxValueNotMustFull(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // 定义状态
        int[][] dp = new int[n+1][capacity + 1];

        // 初始化状态（可以不显式初始化，使用默认值即可）
        Arrays.fill(dp[0], 0);

        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 背包容量为j时，第i个物品能装下，取不装第i个物品(dp[i-1][j])和装第i个物品(dp[i-1][j-weight[i-1]] + values[i])的较大值
                if (j - weights[i-1] >= 0) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i-1]] + values[i-1]);
                }
                // 背包容量为j时，第i个物品不能装下，只能取前i-1个物品的结果
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     *  动态规划(dp[i][j]只与dp[i-1][j]有关，可以优化空间复杂度）
     *      定义状态：dp[j]表示背包容量为j时的最大价值
     *      状态转移：dp[j] = dp'[j] or max(dp'[j], dp'[j-weight[i-1]]+values[i-1])
     */
    public int maxValueNotMustFullV2(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // 定义状态
        int[] dp = new int[capacity + 1];

        // 初始化状态（可以不显式初始化，使用默认值即可）
        Arrays.fill(dp, 0);

        for (int i = 0; i < n; i++) {
            // 注意这里的j一定要倒序遍历，因为是用上一步中j较小的状态来推这一步中j较大的状态
            for (int j = capacity; j > 0; j--) {
                if (j - weights[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }
        return dp[capacity];
    }

}
