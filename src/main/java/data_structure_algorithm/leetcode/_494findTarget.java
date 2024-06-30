package data_structure_algorithm.leetcode;

public class _494findTarget {

    public static class Solution0 {

        /**
         DFS：枚举每个数的两种可能
         时间复杂度：O(2^N)
         空间复杂度：O(N)
         */
        public int findTargetSumWays(int[] nums, int target) {
            dfs(nums, 0, target);
            return count;
        }

        private int count = 0;

        public void dfs(int[] nums, int i, int target) {
            if (i == nums.length) {
                if (target == 0) count++;
                return;
            }
            dfs(nums, i + 1, target + nums[i]);
            dfs(nums, i + 1, target - nums[i]);
        }

    }



    public static class Solution1 {

        /**
         回溯：每个数前面可能是“+”也可能是“—”，枚举每一种可能，计算每种可能的sum
         时间复杂度：(2^N)，指数级别
         空间复杂度：O(N)

         思考：能否用备忘录？不能，因为没有重复子问题
         */
        public int findTargetSumWays(int[] nums, int target) {
            findTarget(nums, target, 0, 0);
            return count;
        }

        private int count = 0;

        private void findTarget(int[] nums, int target, int i, int sum) {
            if (i == nums.length) {
                if (sum == target) {
                    count++;
                }
                return;
            }

            findTarget(nums, target, i + 1, sum + nums[i]);
            findTarget(nums, target, i + 1, sum - nums[i]);
        }

    }



    public static class Solution2 {

        /**
         动态规划：转换为01背包(类似“416.分割等和子集”)
         思路：数组所有元素总和为sum，添加“+”“-”号后结果为target，也就是数组元素之差为target
         将数组分为两部分，一部分前面是"+"，和记为positive，一部分前面是“-”，和记为negative
         那么有：positive + negative = sum, positive - negative = target
         可以得出：positive = (sum + target) / 2, negative = (sum - target) / 2
         由此，可以将求“不同表达式的数目”转换为“求和为positive/negative的组合数”

         时间复杂度：O(N * M)
         空间复杂度：O(N)
         */
        public int findTargetSumWays(int[] nums, int target) {
            // 求和
            int n = nums.length, sum = 0;
            for (int num : nums) {
                sum += num;
            }

            // 排除不可能情况
            if (sum < target) return 0;
            if ((sum - target) % 2 != 0) {
                return 0;
            }

            // 计算和为negative的组合数(01背包问题)
            int negative = (sum - target) / 2;

            // 定义状态
            int[] dp = new int[negative + 1];

            // 初始状态
            dp[0] = 1;

            // 状态转移（求组合数，外层循环是物品，内层循环是背包；01背包，背包重量倒叙遍历）
            for (int num : nums) {
                for (int i = negative; i >= num; i--) {
                    dp[i] += dp[i - num];
                }
            }
            return dp[negative];
        }

    }

}
