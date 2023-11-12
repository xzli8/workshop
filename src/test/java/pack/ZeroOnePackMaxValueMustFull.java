package pack;

import org.junit.Test;

import java.util.Arrays;

public class ZeroOnePackMaxValueMustFull {

    /**
     * 有一组物品，重量是weights，价值是values，现有一容量为capacity的背包，计算出背包能装下的物品的最大价值，要求背包必须装满
     */
    @Test
    public void testMustFull() {
        int[] weights = new int[]{2, 2, 4, 6, 3};
        int[] values = new int[]{3, 4, 8, 9, 6};
        int capacity = 16;
        System.out.println(maxValueMustFull(weights, values, capacity));
        System.out.println(maxValueMustFullV2(weights, values, capacity));
    }

    /**
     * 动态规划
     *      定义状态：dp[i][j]表示对于前i件物品，背包当前重量为j时的最大价值（第i个物品的下标为i-1）
     *      状态转移：状态的转移要从上一层的可行解开始
     *          // 如果第i个物品不能放入背包，只能选择第i-1个物品的结果；如果能放入，需要选择放或者不放的最大值
     *          if (第i个物品不能放入背包) dp[i][j] = dp[i-1][j]
     *          else dp[i][j] = max(dp[i-1][j], dp[i-1][j-weights[i-1]]+values[i-1])
     *      初始状态：
     *          dp[0][0] = 0, 重量为0且一件物品都不放时，背包装满，是可行解
     *          dp[0][j] = -1, 重量不为0且一件物品都不放时，背包未装满，不是可行解
     */
    public int maxValueMustFull(int[] weights, int[] values, int capacity) {
        int n = weights.length;

        // 定义状态
        int[][] dp = new int[n + 1][capacity + 1];

        // 初始化状态
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[0][0] = 0;

        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 背包容量为j时，第i个物品能装下，且上一层的状态合法
                if (j - weights[i - 1] >= 0 && dp[i - 1][j - weights[i - 1]] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     * 优化空间复杂度
     */
    public int maxValueMustFullV2(int[] weights, int[] values, int capacity) {
        // 定义状态
        int[] dp = new int[capacity + 1];

        // 初始化状态
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 状态转移
        for (int i = 0; i < weights.length; i++) {
            for (int j = capacity; j >= 0; j--) {
                // 背包容量为j时，第i个物品能装下，且上一层的状态合法
                if (j - weights[i] >= 0 && dp[j - weights[i]] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
                }
            }
        }
        return dp[capacity];
    }

}
