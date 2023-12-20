package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _739dailyTemperatures {

    public static class Solution1 {

        /**
         单调栈：注意栈中要存下标
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] dailyTemperatures(int[] temperatures) {
            int n = temperatures.length;
            int[] res = new int[n];

            Deque<Integer> s = new LinkedList<>();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && temperatures[s.peek()] <= temperatures[i]) {
                    s.pop();
                }
                res[i] = s.isEmpty() ? 0 : s.peek() - i;
                s.push(i);
            }
            return res;
        }

    }

}
