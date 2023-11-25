package data_structure_algorithm.algorithm.knapsack._03multiple;

import org.junit.Test;

import java.util.Arrays;

public class MaxValue {

    @Test
    public void test() {
        int[] weights = new int[] {1, 3, 4};
        int[] values = new int[] {15, 20, 30};
        int[] nums = new int[] {2, 3, 2};
        int capacity = 10;
        System.out.println(maxValue1(weights, values, nums, capacity));
        System.out.println(maxValue2(weights, values, nums, capacity));
    }



    /**
     *  问题描述：有一堆物品，重量和价值分别为weights[i]和values[i]，每种物品的最大可用数量分别为nums[i]
     *          现在有一个容量为capacity的背包，问能装下的最大价值是多少？
     */
    public int maxValue1(int[] weights, int[] values, int[] nums, int capacity) {
        return 0;
    }

    /**
     *  空间压缩
     */
    public int maxValue2(int[] weights, int[] values, int[] nums, int capacity) {
        // 定义状态
        int[] dp = new int[capacity + 1];

        // 初始状态
        Arrays.fill(dp, -1);
        dp[0] = 0;

        // 状态转移

        return 0;
    }


}
