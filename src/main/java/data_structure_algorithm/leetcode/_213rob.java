package data_structure_algorithm.leetcode;

public class _213rob {

    public static class Solution0 {

        /**
         动态规划
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];
            if (n == 2) return Math.max(nums[0], nums[1]);

            // 定义状态：dp[i][j]表示第i间房屋偷(j = 1)和不偷(j = 0)时的最高金额
            int[][] dp = new int[n][2];

            // 第一间偷，最后一间不可以偷
            dp[0][0] = 0;
            dp[0][1] = nums[0];
            for (int i = 1; i < n - 1; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
            }
            int max1 = Math.max(dp[n - 2][0], dp[n - 2][1]);

            // 第一间不偷，最后一间可以偷
            dp[1][0] = 0;
            dp[1][1] = nums[1];
            for (int i = 2; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i]);
            }
            int max2 = Math.max(dp[n - 1][0], dp[n - 1][1]);
            return Math.max(max1, max2);
        }

    }



    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]表示截止第i个房间能偷到的最大数目
         状态转换：dp[i] = max(dp[i-1], dp[i-2] + nums[i])。第i个房间偷或者不偷，偷的话不能偷第i-1个
         需要单独考虑第一个和最后一个房间
         初始状态：偷第一个房间，不能偷最后一个房间；不偷第一个房间，可以偷最后一个房间

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 1) return nums[0];

            // 定义并初始化状态
            int[] dp = new int[n];

            // 偷第一家，不偷最后一家
            dp[0] = nums[0];
            dp[1] = Math.max(dp[0], dp[1]);
            for (int i = 2; i < n-1; i++) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            }
            int max = dp[n-2];

            // 不偷第一家，偷最后一家
            dp[0] = 0;
            dp[1] = nums[1];
            for (int i = 2; i < n; i++) {
                dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            }
            max = Math.max(max, dp[n-1]);
            return max;
        }

    }



    public static class Solution2 {

        /**
         动态规划：优化空间复杂度为O(1)
         */
        public int rob(int[] nums) {
            // 边界情况
            int n = nums.length;
            if (n == 0) return 0;
            if (n == 1) return nums[0];

            // 打劫第1间，则不能打劫最后一间
            int dp0 = nums[0], dp1 = nums[0];
            for (int i = 2; i < n - 1; i++) {
                int dp2 = Math.max(dp1, dp0 + nums[i]);
                dp0 = dp1;
                dp1 = dp2;
            }
            int res = dp1;

            // 不打劫第一间，则可以打劫最后一间
            dp0 = 0; dp1 = nums[1];
            for (int i = 2; i < n; i++) {
                int dp2 = Math.max(dp1, dp0 + nums[i]);
                dp0 = dp1;
                dp1 = dp2;
            }
            return Math.max(res, dp1);
        }

    }

}
