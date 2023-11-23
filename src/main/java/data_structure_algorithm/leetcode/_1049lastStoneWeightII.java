package data_structure_algorithm.leetcode;

public class _1049lastStoneWeightII {

    /**
     动态规划：将石头分成总重量最接近两堆，转换为01背包问题，与“416-分割等和子集”类似
     一堆总重量为x，另一堆总重量就是sum - x，两堆的差值就是sum - 2x
     （因为x的最大值是向下取整，所以sum - x肯定大于x）

     NOTE：与416的差别，416要求背包装满，本题不要求背包装满

     时间复杂度：O(N * M)
     空间复杂度：O(M)
     */
    public int lastStoneWeightII(int[] stones) {
        // 计算总重量
        int n = stones.length;
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }

        // 定义状态
        int capacity = sum >> 1;
        boolean[] dp = new boolean[capacity + 1];

        // 初始状态
        dp[0] = true;
        if (stones[0] <= capacity) {
            dp[stones[0]] = true;
        }

        // 状态转移
        for (int i = 1; i < n; i++) {
            for (int j = capacity; j >= 0; j--) {
                if (j - stones[i] >= 0 && dp[j - stones[i]]) {
                    dp[j] = true;
                }
            }
        }

        // 找最大值
        int max = 0;
        for (int j = capacity; j >= 0; j--) {
            if (dp[j]) {
                max = j;
                break;
            }
        }

        // 计算差值（也就是最小重量）
        return sum - 2 * max;
    }

}
