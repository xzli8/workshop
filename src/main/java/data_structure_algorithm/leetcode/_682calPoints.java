package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _682calPoints {

    public static class Solution1 {

        /**
         Stack
         */
        public int calPoints(String[] operations) {
            // loop, use stack to simulate
            Deque<Integer> s = new ArrayDeque<>();
            for (String op : operations) {
                if (isScore(op)) {
                    s.push(toInt(op));
                }
                if ("+".equals(op)) {
                    int num1 = s.pop(), num2 = s.peek();
                    s.push(num1);
                    s.push(num1 + num2);
                }
                if ("D".equals(op)) {
                    s.push(2 * s.peek());
                }
                if ("C".equals(op)) {
                    s.pop();
                }
            }

            // calculate final result
            int res = 0;
            while (!s.isEmpty()) {
                res += s.pop();
            }
            return res;
        }

        private boolean isScore(String op) {
            char c = op.charAt(0);
            return c == '-' || ('0' <= c && c <= '9');
        }

        private int toInt(String op) {
            return op.charAt(0) == '-' ? Integer.valueOf(op.substring(1)) * -1 : Integer.valueOf(op);
        }

    }

}
