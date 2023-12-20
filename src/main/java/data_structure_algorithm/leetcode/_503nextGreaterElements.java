package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _503nextGreaterElements {

    public static class Solution1 {

        /**
         单调栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Deque<Integer> s = new LinkedList<>();
            // 假设数组长度翻倍
            for (int i = 2 * n - 1; i >= 0; i--) {
                int index = i % n;
                int num = nums[index];
                while (!s.isEmpty() && s.peek() <= num) {
                    s.pop();
                }
                res[index] = s.isEmpty() ? -1 : s.peek();
                s.push(num);
            }
            return res;
        }

    }

}
