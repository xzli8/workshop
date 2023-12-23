package data_structure_algorithm.leetcode;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

public class _862shortestSubarray {

    public static class Solution1 {

        /**
         前缀和 + 暴力搜素(timeout)
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int shortestSubarray(int[] nums, int k) {
             // 计算前缀和
             int n = nums.length;
             long[] preSum = new long[n + 1];
             for (int i = 1; i <= n; i++) {
                 preSum[i] = preSum[i - 1] + nums[i - 1];
             }

             // 遍历前缀和区间，求区间和至少为k的最短非空子数组
             int minLen = Integer.MAX_VALUE;
             for (int i = 0; i <= n; i++) {
                 for (int j = i + 1; j <= n; j++) {
                     if (preSum[j] - preSum[i] >= k) {
                         minLen = Math.min(minLen, j - i);
                     }
                 }
             }
             return minLen == Integer.MAX_VALUE ? -1 : minLen;
         }

    }



    public static class Solution2 {

        /**
         前缀和 + 单调队列
         思路：计算前缀和，前缀和的差即为子数组的和；用单调队列降低时间复杂度。
         参考题解：https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/solutions/1925036/liang-zhang-tu-miao-dong-dan-diao-dui-li-9fvh/

         时间复杂度：O(N)，每个元素最多只会入队一次，出队一次
         空间复杂度：O(N)
         */
        public int shortestSubarray(int[] nums, int k) {
            // 计算前缀和
            int n = nums.length;
            long[] preSum = new long[n+1];  // 用long类型防止溢出
            for (int i = 0; i < n; i++) {
                preSum[i+1] = preSum[i] + nums[i];
            }

            // 遍历元素，维护单调(递增)队列
            int res = n + 1;
            Deque<Integer> q = new ArrayDeque<>(); // 队列中记录元素下标
            for (int i = 0; i <= n; i++) {
                // 移除队列左侧元素
                while (!q.isEmpty() && preSum[i] - preSum[q.peekFirst()] >= k) {
                    res = Math.min(res, i - q.pollFirst());
                }
                // 移除队列右侧元素，维持单调队列递增
                while (!q.isEmpty() && preSum[i] <= preSum[q.peekLast()]) {
                    q.pollLast();
                }
                q.addLast(i);
            }
            return res > n ? -1 : res;
        }

    }



    public static class Solution3 {

        /**
         前缀和 + 单调队列：在遍历数组元素的同时计算前缀和，需要在单调队列中多记录前缀和对应的下标
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int shortestSubarray(int[] nums, int k) {
            // 初始化
            int n = nums.length, minLen = Integer.MAX_VALUE;
            long preSum = 0;
            Deque<Pair<Long, Integer>> dq = new ArrayDeque<>();
            dq.offerLast(new Pair<>(0L, -1));

            // 一边遍历一边计算前缀和
            for (int i = 0; i < n; i++) {
                // 计算前缀和
                preSum += nums[i];

                // 满足条件时计算前缀和并且删除多余元素
                while (!dq.isEmpty() && preSum - dq.peekFirst().getKey() >= k) {
                    minLen = Math.min(minLen, i - dq.pollFirst().getValue());
                }

                // 维持队列递增
                while (!dq.isEmpty() && dq.peekLast().getKey() >= preSum) {
                    dq.pollLast();
                }

                // 前缀和加入队列
                dq.offerLast(new Pair<>(preSum, i));
            }
            return minLen == Integer.MAX_VALUE ? -1 : minLen;
        }

    }


}
