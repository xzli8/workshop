package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1475finalPrices {

    public static class Solution1 {

        /**
         单调栈(从栈底到栈顶，元素依次递增)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] finalPrices(int[] prices) {
            int n = prices.length;
            int[] res = new int[n];
            Deque<Integer> s = new ArrayDeque<>(n);
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && s.peek() > prices[i]) {
                    s.pop();
                }
                res[i] = s.isEmpty() ? prices[i] : prices[i] - s.peek();
                s.push(prices[i]);
            }
            return res;
        }

    }

}
