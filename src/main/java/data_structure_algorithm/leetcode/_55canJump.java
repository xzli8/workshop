package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _55canJump {

    public static class Solution1 {

        /**
         动态规划
         定义状态：dp[i]表示nums[i]是否可达
         状态转移：dp[i] = dp[j] && j + nums[j] >= i
         初始状态：dp[0] = true, 第一个位置一定可达

         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public boolean canJump(int[] nums) {
             int n = nums.length;
             if (n == 1) return true;

             // 定义状态
             boolean[] dp = new boolean[n];

             // 初始状态
             dp[0] = true;

             // 状态转移
             for (int i = 1; i < n; i++) {
                 for (int j = 0; j < i; j++) {
                     if (dp[j] && j + nums[j] >= i) {
                         dp[i] = true;
                         break;
                     }
                 }
             }
             return dp[n-1];
         }

    }



    public static class Solution2 {

        /**
         贪心：从前往后遍历，每次找能到达的最远距离
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean canJump(int[] nums) {
            int n = nums.length, lastPosition = 0;
            for (int i = 0; i <= lastPosition; i++) {
                lastPosition = Math.max(lastPosition, i + nums[i]);
                if (lastPosition >= n - 1) {
                    return true;
                }
            }
            return false;
        }

    }



    public static class Solution3 {
        /**
         贪心：从后往前遍历，寻找能到达的最前面的元素，判断该元素是否为第一个元素
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public boolean canJump(int[] nums) {
             int n = nums.length;
             int lastPosition = n-1;    // 最后一个元素一定可达
             for (int i = n-2; i >= 0; i--) {
                 if (i + nums[i] >= lastPosition) {
                     lastPosition = i;
                 }
             }
             return lastPosition == 0;
         }
    }


}
