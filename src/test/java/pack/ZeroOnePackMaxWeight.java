package pack;

import org.junit.Test;

import java.util.Arrays;

public class ZeroOnePackMaxWeight {


    /**
     * 有一组物品，重量是weights，现有一容量为capacity的背包，计算出背包能装下物品的最大重量，不要求背包必须装满
     */
    @Test
    public void testMaxWeight() {
        int[] weights = new int[] {2, 2, 4, 6, 3};
        int capacity = 16;
        System.out.println(maxWeightNotMustFullBacktrace(weights, capacity));
        System.out.println(maxWeightNotMustFull(weights, capacity));
    }

    /**
     * 回溯（带备忘录）：带备忘录的回溯和动态规划时间/空间复杂度没区别
     */
    public int maxWeightNotMustFullBacktrace(int[] weights, int capacity) {
        visited = new boolean[weights.length][capacity + 1];
        backtrace(weights, capacity, 0, 0);
        return maxWeight;
    }

    private int maxWeight;

    private boolean[][] visited;

    public void backtrace(int[] weights, int capacity, int i, int totalWeight) {
        if (i == weights.length || totalWeight == capacity) {
            maxWeight = Math.max(maxWeight, totalWeight);
            return;
        }

        // 第i个物品且当前重量为totalWeight的情形已经计算过了，跳过
        if (visited[i][totalWeight]) return;

        // 更新备忘录
        visited[i][totalWeight] = true;

        // 不放第i个物品
        backtrace(weights, capacity, i + 1, totalWeight);

        // 放第i个物品
        if (totalWeight + weights[i] <= capacity) {
            backtrace(weights, capacity, i + 1, totalWeight + weights[i]);
        }
    }

    /**
     * 动态规划
     */
    public int maxWeightNotMustFull(int[] weights, int capacity) {
        int n = weights.length;

        // 定义状态：dp[j]表示容量为j的背包是否能被装满
        boolean[] dp = new boolean[capacity + 1];

        // 初始化状态（可以不显式初始化，使用默认值）
        Arrays.fill(dp, false);
        dp[0] = true;

        // 状态转移
        for (int i = 0; i < n; i++) {
            for (int j = capacity; j >= 0; j--) {
                // 背包容量为j时，第i个物品能装下，且上一层的状态合法
                if (j - weights[i] >= 0 && dp[j - weights[i]]) {
                    dp[j] = true;
                }
            }
        }

        // 找最大值
        for (int i = capacity; i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }
        return 0;
    }


}
