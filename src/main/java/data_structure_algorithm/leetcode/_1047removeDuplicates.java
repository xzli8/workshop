package data_structure_algorithm.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class _1047removeDuplicates {

    public static class Solution1 {

        /**
         栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String removeDuplicates(String s) {
            Deque<Character> stack = new LinkedList<>();
            for (char c : s.toCharArray()) {
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    char top = stack.peek();
                    if (top == c) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }

    }

}
