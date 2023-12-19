package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _239maxSlidingWindow {

    public static class Solution1 {

        /**
         双端队列（参考“面试题59” https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/）
         */
        public int[] maxSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            int[] res = new int[n - k + 1];

            // 初始化deque
            Deque<Integer> dq = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                push(dq, nums[i]);
            }
            res[0] = dq.peekFirst();

            // 遍历数组
            for (int i = k; i < n; i++) {
                if (nums[i - k] == dq.peekFirst()) dq.pollFirst();  // 出队(这里不太妥当，单调队列中放下标更好)
                push(dq, nums[i]); // 入队
                res[i - k + 1] = dq.peekFirst();
            }
            return res;
        }

        private void push(Deque<Integer> dq, Integer num) {
            while (!dq.isEmpty() && dq.peekLast() < num) {
                dq.pollLast();
            }
            dq.offerLast(num);
        }

    }

}
