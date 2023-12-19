package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _150evalRPN {

    public static class Solution1 {

        /**
         栈：遍历tokens数组，用栈存操作数。当遇到操作符时，弹出两个操作数进行运算，将结果压入栈中。遍历完成后，栈中就是结果
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int evalRPN(String[] tokens) {
            Deque<Integer> s = new ArrayDeque<>();
            for (String token : tokens) {
                // 如果token是操作数，入栈
                if (!isOp(token)) {
                    s.push(Integer.valueOf(token));
                }
                // 如果token是操作符，从栈中弹出两个操作数，将计算结果压入栈
                else {
                    // 这里要注意弹出的顺序(加法乘法满足交换律，顺序无所谓；减法和除法不满足交换律，顺序有所谓)
                    Integer num2 = s.pop();
                    Integer num1 = s.pop();
                    s.push(calculate(num1, num2, token));
                }
            }
            return s.peek();
        }

        private boolean isOp(String token) {
            return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
        }

        private int calculate(int num1, int num2, String token) {
            int res = 0;
            if ("+".equals(token)) {
                res = num1 + num2;
            }
            else if ("-".equals(token)) {
                res = num1 - num2;
            }
            else if ("*".equals(token)) {
                res = num1 * num2;
            }
            else if ("/".equals(token)) {
                res = num1 / num2;
            }
            return res;
        }

    }

}
