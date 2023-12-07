package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Deque;
import java.util.List;

public class _216combinationSum3 {

    public static class Solution1 {

        /**
         回溯
         */
        public List<List<Integer>> combinationSum3(int k, int n) {
            backtrace(k, n, 1, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int k, int n, int start, LinkedList<Integer> path) {
            if (k == 0) {
                if (n == 0) {
                    res.add(new LinkedList<>(path));
                }
                return;
            }

            for (int i = start; i <= 9; i++) {
                if (k - 1 < 0 || n - i < 0) continue;   // 剪枝
                path.addLast(i);
                backtrace(k - 1, n - i, i + 1, path);
                path.removeLast();
            }
        }

    }

}
