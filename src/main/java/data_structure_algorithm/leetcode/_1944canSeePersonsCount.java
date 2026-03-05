package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1944canSeePersonsCount {

    public static class Solution1 {

        /**
         MonotonicStack
         */
        public int[] canSeePersonsCount(int[] heights) {
            int n = heights.length;
            int[] res = new int[n];
            Deque<Integer> s = new ArrayDeque<>();  // 记录下一个更大元素的下标
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && heights[s.peek()] < heights[i]) {
                    s.pop();
                    res[i]++;
                }
                if (!s.isEmpty()) res[i]++;     // 总能看到下一个更大的
                s.push(i);
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         单调栈:O(N), O(N)
         NOT WORK !!!
         case: [10, 6, 8, 5, 11, 9]
         error: [4,1,2,1,1,0]
         correct: [3,1,2,1,1,0]
         */
        public int[] canSeePersonsCount(int[] heights) {
            // 计算下一个更大元素的下标
            int n = heights.length;
            int[] nextGreaterIndex = new int[n];
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while(!s.isEmpty() && heights[i] >= heights[s.peek()]) {
                    s.pop();
                }
                nextGreaterIndex[i] = s.isEmpty() ? n - 1 : s.peek();
                s.push(i);
            }

            // 根据下标计算数量
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                res[i] = nextGreaterIndex[i] - i;
            }
            return res;
        }

    }

}
