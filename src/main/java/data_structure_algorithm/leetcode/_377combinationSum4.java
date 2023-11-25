package data_structure_algorithm.leetcode;

public class _377combinationSum4 {

    /**
     动态规划：转换为完全背包求排列数问题
     思路：元素可以重复使用 -> 完全背包；顺序不同的序列被视为不同的组合 -> 排列数

     定义状态：dp[i]表示和为i的排列数
     状态转移：dp[i] += dp[i - num], num in nums
     初始状态：dp[0] = 1，总和为0的排列数有1种

     时间复杂度：O(N * M)
     空间复杂度：O(M)
     */
    public int combinationSum4(int[] nums, int target) {
        // 定义状态
        int[] dp = new int[target + 1];

        // 初始状态
        dp[0] = 1;

        // 状态转移
        // 求排列数：先遍历背包，再遍历物品
        for (int i = 0; i <= target; i++) {
            //完全背包：内循环从小到大遍历
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

}
