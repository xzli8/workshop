package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class _1696maxResult {

    public static class Solution1 {

        /**
         DP: O(N * K), O(N) -> TLE
         */
        public int maxResult(int[] nums, int k) {
            int n = nums.length;

            // 定义状态：dp[i]表示截止第i个元素的最大得分
            int[] dp = new int[n];

            // 初始状态
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = nums[0];

            // 状态转移
            for (int i = 1; i < n; i++) {
                for (int j = Math.max(0, i - k); j < i; j++) {
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }
            return dp[n - 1];
        }

    }


    public static class Solution2 {

        /**
         DP + Queue: O(N), O(N)
         Ref: https://leetcode.cn/problems/jump-game-vi/solutions/2631981/yi-bu-bu-you-hua-cong-di-gui-dao-di-tui-84qn3/
         */
        public int maxResult(int[] nums, int k) {
            int n = nums.length;

            // 定义状态：dp[i]表示截止第i个元素的最大得分
            int[] dp = new int[n];

            // 初始状态
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = nums[0];
            Deque<Integer> q = new ArrayDeque<>();  // 单调递减队列(按照dp单调递减，q中存下标)
            q.offer(0);

            // 状态转移
            for (int i = 1; i < n; i++) {
                if (q.peekFirst() < i - k) q.pollFirst();   // 超出范围，删除
                dp[i] = dp[q.peekFirst()] + nums[i];
                while (!q.isEmpty() && dp[i] >= dp[q.peekLast()]) q.pollLast();    // 维持单调性
                q.offer(i);
            }
            return dp[n - 1];
        }

    }

}
