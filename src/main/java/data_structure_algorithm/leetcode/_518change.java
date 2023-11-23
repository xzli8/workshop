package data_structure_algorithm.leetcode;

public class _518change {

    /**
     动态规划
     定义状态：dp[i]表示凑齐总金额为i的组合数
     状态转移：dp[i] += dp[i - coin], coin in coins。
     对于面额为coin的硬币，如果存在一种组合的总和为i-coin，则在组合中增加一枚面额为coin的硬币即可
     初始状态：dp[0] = 1，凑齐金额为0的组合数有1种

     时间复杂度：O(amount * coins.length)
     空间复杂度：O(amount)

     */
    public int change(int amount, int[] coins) {
        // 定义状态
        int[] dp = new int[amount+1];

        // 初始状态：凑够总金额为0无需任何硬币，组合数为1（注意不是0）
        dp[0] = 1;

        // 状态转移
        // 注意这里先对coins循环，然后对amount循环，保证每个coin只考虑一次，从而得到组合数
        // 如果这里先对amount循环，再对coins循环，则每个coin会被多次考虑，得到的是排列数
        for (int coin : coins) {
            // 完全背包：内循环从小到大开始遍历
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

}
