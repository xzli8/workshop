package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _784letterCasePermutation {

    public static class Solution1 {

        // DFS: O(2^L * N), O(N)
        public List<String> letterCasePermutation(String s) {
            dfs(s.toCharArray(), 0);
            return res;
        }

        private List<String> res = new ArrayList<>();
        private void dfs(char[] cs, int i) {
            if (i == cs.length) {
                res.add(new String(cs));
                return;
            }

            if (Character.isLetter(cs[i])) {
                cs[i] = Character.toLowerCase(cs[i]);
                dfs(cs, i + 1);
                cs[i] = Character.toUpperCase(cs[i]);
                dfs(cs, i + 1);
            } else {
                dfs(cs, i + 1);
            }
        }

    }


    public static class Solution2 {

        // BFS: O(2^L * N), O(N)
        public List<String> letterCasePermutation(String s) {
            List<String> res = new ArrayList<>();
            res.add("");
            for (char c : s.toCharArray()) {
                List<String> next = new ArrayList<>();
                for (String prev : res) {
                    if (Character.isLetter(c)) {
                        next.add(prev + Character.toLowerCase(c));
                        next.add(prev + Character.toUpperCase(c));
                    } else {
                        next.add(prev + c);
                    }
                }
                res = next;
            }
            return res;
        }

    }


    public static class Solution3 {

        // backtrace: O(2^N), O(2^N)
        public List<String> letterCasePermutation(String s) {
            backtrace(s.toCharArray(), 0);
            return res;
        }

        private final List<String> res = new ArrayList<>();
        private void backtrace(char[] c, int index) {
            while (index < c.length && Character.isDigit(c[index])) {
                index++;
            }
            if (index == c.length) {
                res.add(String.valueOf(c));
                return;
            }

            backtrace(c, index + 1);

            c[index] ^= 32;
            backtrace(c, index + 1);
            c[index] ^= 32;
        }

    }

}
