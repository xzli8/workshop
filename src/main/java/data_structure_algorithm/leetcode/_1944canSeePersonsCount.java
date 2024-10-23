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
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && heights[s.peek()] < heights[i]) {
                    s.pop();
                    res[i]++;
                }
                if (!s.isEmpty()) res[i]++;
                s.push(i);
            }
            return res;
        }

    }

}
