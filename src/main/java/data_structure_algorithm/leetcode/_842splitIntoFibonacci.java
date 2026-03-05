package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _842splitIntoFibonacci {

    public static class Solution1 {

        /**
         backtrace:
         */
        public List<Integer> splitIntoFibonacci(String num) {
            dfs(num, 0);
            return path;
        }

        private List<Integer> path = new ArrayList<>();

        private boolean dfs(String num, int start) {
            if (start == num.length()) {
                return path.size() >= 3;
            }

            long x = 0;
            for (int end = start; end < num.length(); end++) {
                if (end > start && num.charAt(start) == '0') break;
                x = x * 10 + num.charAt(end) - '0';
                if (x > Integer.MAX_VALUE) break;
                if (path.size() >= 2 && x > path.get(path.size() - 1) + path.get(path.size() - 2)) break;   // 剪枝(锦上添花，不要也行)
                if (path.size() < 2 || x == path.get(path.size() - 1) + path.get(path.size() - 2)) {
                    path.add((int) x);
                    if (dfs(num, end + 1)) return true;
                    path.remove(path.size() - 1);
                }
            }
            return false;
        }

    }


    public static class Solution2 {

        /**
         backtrace:
         Note: 这种做法可以找出全部的解
         */
        public List<Integer> splitIntoFibonacci(String num) {
            dfs(num, 0, new ArrayList<>());
            return paths.size() > 0 ? paths.get(0) : new ArrayList<>();
        }

        private List<List<Integer>> paths = new ArrayList<>();

        private void dfs(String num, int start, List<Integer> path) {
            if (start == num.length()) {
                if (path.size() >= 3) {
                    paths.add(new ArrayList<>(path));
                }
                return;
            }

            long x = 0;
            for (int end = start; end < num.length(); end++) {
                if (end > start && num.charAt(start) == '0') break;
                x = x * 10 + num.charAt(end) - '0';
                if (x > Integer.MAX_VALUE) break;
                if (path.size() >= 2 && x > path.get(path.size() - 1) + path.get(path.size() - 2)) break;   // 剪枝(锦上添花，不要也行)
                if (path.size() < 2 || x == path.get(path.size() - 1) + path.get(path.size() - 2)) {
                    path.add((int) x);
                    dfs(num, end + 1, path);
                    path.remove(path.size() - 1);
                }
            }
        }

        @Test
        public void test() {
            System.out.println(splitIntoFibonacci("1101111"));
        }

    }

}
