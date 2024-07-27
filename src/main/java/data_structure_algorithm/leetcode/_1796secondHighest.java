package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.TreeSet;

public class _1796secondHighest {

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
