package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _20isValid {

    public static class Solution1 {

        /**
         栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isValid(String s) {
            Deque<Character> stack = new ArrayDeque<>();
            for (char c : s.toCharArray()) {
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.isEmpty()) return false;
                    if (match(stack.peek(), c)) stack.pop();
                    else stack.push(c);
                }
            }
            return stack.isEmpty();
        }

        private boolean match(char lc, char rc) {
            return (lc == '(' && rc == ')') || (lc == '[' && rc == ']') || (lc == '{' && rc == '}');
        }

    }

}
