package data_structure_algorithm.leetcode.supplyment;

import org.junit.Test;

public class ClimbStairs {

    /**
     *  爬楼梯进阶：有n阶楼梯，每次最多能爬m阶（这里也可以不给m，给一个数组表示每次能爬的阶数，原理一样），问有多少种爬到楼顶的方法
     *
     *      思路：动态规划 -> 完全背包求排列数
     *
     *      定义状态：dp[i]表示爬到第i阶楼梯的方法
     *      状态转移：dp[i] += dp[i - j], j = 0,..., i - m
     *      初始状态：dp[0] = 1
     *
     *      时间复杂度：O(N * M)
     *      空间复杂度：O(N)
     */
    public int climbStairs(int n, int m) {
        // 定义状态
        int[] dp = new int[n + 1];

        // 初始状态
        dp[0] = 1;

        // 状态转移（注意i, j要从1而不是0开始遍历）
        // 求排列数：先遍历背包，再遍历物品
        for (int i = 1; i <= n; i++) {
            // 完全背包：内循环从小到大遍历
//            for (int j = 1; j <= m; j++) {
//                if (i - j >= 0) {
//                    dp[i] += dp[i - j];
//                }
//            }

            for (int j = 1; j <= Math.min(i, m); j++) {
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        System.out.println(climbStairs(3, 2));
    }

}
