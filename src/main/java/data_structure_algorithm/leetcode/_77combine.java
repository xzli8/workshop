package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _77combine {

    public static class Solution1 {

        // 回溯
        public List<List<Integer>> combine(int n, int k) {
            backtrace(n, k, 1, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int n, int k, int start, Deque<Integer> path) {
            if (k == 0) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i <= n; i++) {
                path.offerLast(i);
                backtrace(n, k - 1, i + 1, path);
                path.pollLast();
            }
        }

    }

}
