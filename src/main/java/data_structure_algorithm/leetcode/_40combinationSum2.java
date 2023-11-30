package data_structure_algorithm.leetcode;

import java.util.*;

public class _40combinationSum2 {

    public static class Solution1 {

        // 回溯
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            backtrace(candidates, target, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int[] candidates, int target, int start, Deque<Integer> path) {
            if (target == 0) {
                res.add(new ArrayList<>(path));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] < 0) continue;
                if (i > start && candidates[i] == candidates[i-1]) continue;
                path.offerLast(candidates[i]);
                backtrace(candidates, target - candidates[i], i + 1, path);
                path.pollLast();
            }
        }

    }

}
