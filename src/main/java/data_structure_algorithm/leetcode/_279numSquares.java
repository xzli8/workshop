package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _279numSquares {

    /**
     动态规划：完全背包求最值
     定义状态：dp[i]表示和为i的完全平方数的最少数量
     状态转移：dp[i] = min(dp[i], dp[i - j * j] + 1), j * j <= i
     初始状态：dp[i] = MAX, dp[0] = 0

     时间复杂度：O(N * sqrt(N))
     空间复杂度：O(N)
     */
    public int numSquares(int n) {
        // 定义状态
        int[] dp = new int[n + 1];

        // 初始状态
        Arrays.fill(dp, n + 1);  // 这里不能初始化为Integer.MAX_VALUE，因为后面有dp[i] + 1，造成超限
        dp[0] = 0;

        // 状态转移
        // 求最值：遍历顺序无所谓，可以先遍历背包再遍历物品，也可以先遍历物品再遍历背包

        // 先遍历背包，再遍历物品
        // for (int i = 1; i <= n; i++) {  // 遍历背包
        //     // 完全背包，内循环从小到大遍历
        //     for (int j = 1; j * j <= i; j++) {  // 遍历物品
        //         dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
        //     }
        // }

        // 先遍历物品，再遍历背包
        for (int j = 1; j * j <= n; j++) {  // 遍历物品
            for (int i = j * j; i <= n; i++) {  // 遍历背包
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

}
