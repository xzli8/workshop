package data_structure_algorithm.algorithm.knapsack._01zeroOne;

import org.junit.Test;

import java.util.Arrays;

public class MaxValue {

    /**
     * 有一组物品，重量是weights，价值是values，现有一容量为capacity的背包，计算出背包能装下的物品的最大价值
     */
    @Test
    public void test() {
        int[] weights = new int[] {2, 2, 3, 1, 5, 2};
        int[] values = new int[] {2, 3, 1, 5, 4, 3};
        int capacity = 1;
        System.out.println(maxValue0(weights, values, capacity));
        System.out.println(maxValue1(weights, values, capacity));
        System.out.println(maxValue2(weights, values, capacity));
    }



    /**
     * 回溯（分别考察每个物品，每个物品都有两个选择，装或者不装）
     *      也可以使用备忘录（用i和weight作为key，value作为value，用map存储，相同key取较大的value）
     *      但使用备忘录不一定快，因为不能保证每次都先遍历到value最大的路径，所以还是要遍历所有可能解，备忘录并没有起到效果
     *
     *      时间复杂度：指数
     *      空间复杂度：O(N)
     */
    public int maxValue0(int[] weights, int[] values, int capacity) {
        maxValue = -1;
        backtrace(weights, values, capacity, 0, 0, 0);
        return maxValue;
    }

    private int maxValue;

    private void backtrace(int[] weights, int[] values, int capacity, int i, int weight, int value) {
        // 背包不必装满
        if (i == weights.length || weight == capacity) {
            maxValue = Math.max(maxValue, value);
            return;
        }

        // 背包必须装满
//        if (i == weights.length) {
//            if (weight == capacity) {
//                maxValue = Math.max(maxValue, value);
//            }
//            return;
//        }

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
     *      状态转移：如果第i个物品能放入背包，且第i-1个物品对应的状态合法，选择放入第i个物品或者不放第i个物品的最大值；否则，选择第i-1个物品的结果
     *          if (第i个物品能放入 && 第i-1个物品对应的状态合法) dp[i][j] = max(dp[i-1][j], dp[i-1][j-weight[i]] + values[i]
     *          else dp[i][j] = dp[i-1][j]
     *      初始状态：dp[0] = -1, dp[0][0] = 0, dp[0][weights[0]] = weights[0] <= capacity ? values[0] : 0;
     */
    public int maxValue1(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[][] dp = new int[n][capacity + 1];

        // 初始状态
        Arrays.fill(dp[0], -1);
        dp[0][0] = 0;
        if (weights[0] <= capacity) {
            dp[0][weights[0]] = values[0];
        }

        // 状态转移
        for (int i = 1; i < n; i++) {

//            // 当第i个物品能放下时，可以选择不放或者放第i个物品两种选择中价值最大的那个；当第i个物品放不下时，只能不放
//            for (int j = 0; j <= capacity; j++) {
//                if (j - weights[i] >= 0 && dp[i - 1][j - weights[i]] >= 0) {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
//                } else {
//                    dp[i][j] = dp[i - 1][j];
//                }
//            }

            // 先不放第i个物品，也就是先把i-1的状态copy一份过来；然后考虑是否放第i个物品，当能放下时，选择放或者不放的最大值
            for (int j = 0; j <= capacity; j++) {
                // 第i个物品不放
                dp[i][j] = dp[i - 1][j];

                // 第i个物品能放下时，选择放或者不放的最大值
                if (j - weights[i] >= 0 && dp[i - 1][j - weights[i]] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i]] + values[i]);
                }
            }

        }

        // 返回最大价值(背包不必装满)
        int maxValue = -1;
        for (int j = capacity; j >= 0; j--) {
            maxValue = Math.max(maxValue, dp[n - 1][j]);
        }
        return maxValue;

        // 返回最大价值(背包必须装满)
//        return dp[n - 1][capacity];
    }


    /**
     *  动态规划：压缩空间。因为dp[i]只与dp[i-1]有关
     */
    public int maxValue2(int[] weights, int[] values, int capacity) {
        // 定义状态
        int n = weights.length;
        int[] dp = new int[capacity + 1];

        // 初始状态
        Arrays.fill(dp, -1);
        dp[0] = 0;
        if (weights[0] <= capacity) {
            dp[weights[0]] = values[0];
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            // 这里j要从大到小遍历，因为状态转移用dp[j - weight[i]]计算dp[j]，也就是用前面的计算后面的
            // 如果从小到大遍历，后面的还没被计算，前面的就已经被覆盖了，不可行
            for (int j = capacity; j >= 0; j--) {
                if (j - weights[i] >= 0 && dp[j - weights[i]] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }

        // 返回最大值(背包不必装满)
        int maxValue = -1;
        for (int j = 0; j <= capacity; j++) {
            maxValue = Math.max(maxValue, dp[j]);
        }
        return maxValue;

        // 返回最大值(背包不必装满)
//        return dp[capacity];
    }

}
