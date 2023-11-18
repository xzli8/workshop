package dsa.leetcode;

public class _746minCostClimbingStairs {

    /**
     动态规划
     定义状态：dp[i]表示爬到下标为i的台阶的最低花费
     状态转移：dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])
     初始状态：dp[0] = 0, dp[1] = 0

     时间复杂度：O(N)
     空间复杂度：O(N)
     */
    public int minCostClimbingStairs1(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[n];
    }

    /**
     动态规划：用变量代替数组来优化空间
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length, s1 = 0, s2 = 0, s3 = 0;
        for (int i = 2; i <= n; i++) {
            s3 = Math.min(s1 + cost[i-2], s2 + cost[i-1]);
            s1 = s2;
            s2 = s3;
        }
        return s3;
    }
}
