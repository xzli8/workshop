package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class _20isValid {

    public static class Solution1 {

        public boolean isValid(String s) {
            Deque<Character> stack = new ArrayDeque<Character>();
            for (int i = 0; i < s.length(); i++) {
                Character c = s.charAt(i);
                if (stack.isEmpty()) {
                    stack.push(c);
                } else {
                    Character top = stack.peek();
                    if (matched(top, c)) {
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
            }
            return stack.isEmpty();
        }

        public static final Map<Character, Character> paris = new HashMap<Character, Character>();
        static {
            paris.put('(', ')');
            paris.put('[', ']');
            paris.put('{', '}');
        }

        public static boolean matched(Character right, Character left) {
            return left.equals(paris.get(right));
        }

    }

}
