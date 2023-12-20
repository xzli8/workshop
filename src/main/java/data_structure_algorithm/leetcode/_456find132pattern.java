package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _456find132pattern {

    public static class Solution1 {

        /**
         单调栈:维护一个单调递减栈，用k(132中的2)记录出栈元素的最大值。当遍历到的元素i小于k时，说明找到了132模式
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean find132pattern(int[] nums) {
            int n = nums.length;
            int k = Integer.MIN_VALUE;
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                if (nums[i] < k) return true;
                // 为了符合32模式，这里s.peek()要严格小于nums[i]
                while (!s.isEmpty() && s.peek() < nums[i]) {
                    k = s.poll();
                }
                s.push(nums[i]);
            }
            return false;
        }

    }

}
