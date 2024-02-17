package data_structure_algorithm.leetcode;

public class _198rob {

    public static class Solution0 {

        /**
         动态规划
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int rob(int[] nums) {
            // 定义状态：dp[i][j]表示第i间房屋偷(j = 1)和不偷(j = 0)时的最高金额
            int n = nums.length;
            int[][] dp = new int[n][2];

            // 初始状态
            dp[0][0] = 0;
            dp[0][1] = nums[0];

            // 状态转移
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
            }
            return Math.max(dp[n - 1][0], dp[n - 1][1]);
        }

    }



    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]表示截止第i个房间能偷到的最多的钱
         状态转移：dp[i] = max(dp[i-1], dp[i-2] + nums[i])
         每个房间可以选择偷或者不偷。如果偷，那么不能偷第i-1个房间；如果不偷，可以偷第i-1个房间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];

            // 定义状态
            int[] dp = new int[n];

            // 初始状态
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            // 状态转移
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            }
            return dp[n-1];
        }


    }



    public static class Solution2 {

        /**
         优化空间：用临时变量替代数组
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int rob(int[] nums) {
             int n = nums.length;
             if (n == 1) return nums[0];

             int n2 = nums[0], n1 = Math.max(nums[0], nums[1]);
             for (int i = 2; i < n; i++) {
                 int n3 = Math.max(n1, n2 + nums[i]);
                 n2 = n1;
                 n1 = n3;
             }
             return n1;
         }


    }



}
