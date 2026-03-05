package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.TreeSet;

public class _1796secondHighest {

    public static class Solution0 {

        /**
         遍历: O(N), O(1)
         */
        public int secondHighest(String s) {
            int highest = -1, secondHighest = -1;
            for (char c : s.toCharArray()) {
                if (!isDigit(c)) continue;
                int n = c - '0';
                if (n > highest) {
                    secondHighest = highest;
                    highest = n;
                } else if (secondHighest < n && n < highest) {
                    secondHighest = n;
                }
            }
            return secondHighest;
        }

        private boolean isDigit(char c) {
            return '0' <= c && c <= '9';
        }

    }

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(secondHighest("ck077"));
        }

        /**
         TreeSet
         */
        public int secondHighest(String s) {
            TreeSet<Character> ts = new TreeSet();
            for (char c : s.toCharArray()) {
                if (isDigit(c)) ts.add(c);
            }
            if (ts.size() < 2) return -1;
            ts.remove(ts.last());
            return ts.last() - '0';
        }

        private boolean isDigit(char c) {
            return '0' <= c && c <= '9';
        }

    }

}
