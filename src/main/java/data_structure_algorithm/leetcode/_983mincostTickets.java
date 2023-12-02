package data_structure_algorithm.leetcode;

public class _983mincostTickets {

    public static class Solution1 {

        /**
         动态规划
         参考题解：https://leetcode.cn/problems/minimum-cost-for-tickets/solutions/233336/java-dong-tai-gui-hua-si-lu-bu-zou-cong-hou-xiang-/

         核心思路：当天出门有三个选择，买1天/7天/30天的票，具体选择哪种，需要看后面的出行计划，
         即当天的选择依赖后面的出行计划，需要从后往前递推
         定义状态：dp[i]表示从第i天开始，所需的最小费用
         状态转移：dp[i] = min(dp[i+1] + cost[0], dp[i+7] + cost[1], dp[i+30] + cost[2])
         初始状态：dp[i] = 0

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int mincostTickets(int[] days, int[] costs) {
            // 定义状态(dp映射到时间轴上)
            int n = days.length, maxDay = days[n - 1], minDay = days[0];
            int[] dp = new int[maxDay + 31];    // 多扩几天，省得判断365的限制

            // 初始状态
            // 数组元素默认初始化为0，可省略

            // 状态转移
            for (int i = maxDay, idx = n - 1; i >= minDay; i--) {
                // 第i天有出行计划，取三种选择的最小值
                if (i == days[idx]) {
                    dp[i] = Math.min(Math.min(dp[i + 1] + costs[0], dp[i + 7] + costs[1]), dp[i + 30] + costs[2]);

                    // 这次旅行结束，idx--
                    idx--;
                }
                // 第i天没有出行计划，不用花钱，等于第i+1天的结果
                else {
                    dp[i] = dp[i + 1];
                }
            }
            return dp[minDay];
        }

    }

}
