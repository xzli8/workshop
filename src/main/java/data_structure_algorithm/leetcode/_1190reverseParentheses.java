package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1190reverseParentheses {

    public static class Solution1 {

        /**
         栈模拟
         */
        public String reverseParentheses(String s) {
            int n = s.length();
            Deque<Character> stack = new ArrayDeque<>(n);

            for (char c : s.toCharArray()) {
                // 不是“右括号”的字符都放到栈中
                if (c != ')') {
                    stack.push(c);
                } else {
                    // 当前字符是“右括号”，不断pop直到遇到“左括号”
                    StringBuilder sb = new StringBuilder();
                    while (stack.peek() != '(') {
                        sb.append(stack.pop());
                    }

                    // 将“左括号”弹出
                    stack.pop();

                    // 将这部分结果再依次入栈
                    for (char sc : sb.toString().toCharArray()) {
                        stack.push(sc);
                    }
                }
            }

            // 遍历栈，将所有结果取出，这时候是逆序的，所以最后需要reverse
            StringBuilder res = new StringBuilder();
            while (!stack.isEmpty()) {
                res.append(stack.pop());
            }
            return res.reverse().toString();
        }

    }

}
