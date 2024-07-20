package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class LCR_183maxSlidingWindow {

    public static class Solution1 {

        // Deque（参考“队列的最大值”）
        public int[] maxSlidingWindow(int[] nums, int k) {

            int n = nums.length;
            int[] res = new int[n - k + 1];

            // init deque
            Deque<Integer> q = new LinkedList<>();
            for (int i = 0; i < k; i++) {
                push(q, nums[i]);
            }
            res[0] = q.peekFirst();

            // 遍历
            for (int i = k; i < n; i++) {
                if (nums[i - k] == q.peekFirst()) q.pollFirst();
                push(q, nums[i]);
                res[i - k + 1] = q.peekFirst();
            }
            return res;
        }

        private void push(Deque<Integer> q, int num) {
            while (!q.isEmpty() && q.peekLast() < num) {
                q.pollLast();
            }
            q.offerLast(num);
        }

    }

}
