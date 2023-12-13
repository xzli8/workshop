package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _862shortestSubarray {

    public static class Solution1 {

        /**
         滑动窗口:添加负数之后窗口的滑动就没有单向性了，因此无法使用滑动窗口解决
         Error, case: [84,-37,32,40,95] 167
         */
         public int shortestSubarray(int[] nums, int k) {
             int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
             while (end < nums.length) {
                 sum += nums[end];
                 end++;

                 while (sum >= k) {
                     minLen = Math.min(minLen, end - start);
                     sum -= nums[start];
                     start++;
                 }
             }
             return minLen > nums.length ? -1 : minLen;
         }

    }



    public static class Solution2 {

        /**
         前缀和 + 单调队列
         思路：计算前缀和，前缀和的差即为子数组的和；用单调队列降低时间复杂度
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


}
