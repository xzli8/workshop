package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _962maxWidthRamp {

    public static class Solution1 {

        /**
         单调栈: O(N), O(N)
         */
        public int maxWidthRamp(int[] nums) {
            // 构建单调递减栈
            int n = nums.length;
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (s.isEmpty() || nums[i] < nums[s.peek()]) {
                    s.push(i);
                }
            }

            // 逆序遍历
            int maxLen = 0;
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && nums[i] - nums[s.peek()] >= 0) {
                    maxLen = Math.max(maxLen, i - s.pop());
                }
            }
            return maxLen;
        }

    }

}
