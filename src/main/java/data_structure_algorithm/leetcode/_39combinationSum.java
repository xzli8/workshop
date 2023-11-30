package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _39combinationSum {

    public static class Solution1 {

        // 回溯 + 剪枝
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
                if (target - candidates[i] < 0) continue;   // 剪枝
                path.offerLast(candidates[i]);
                backtrace(candidates, target - candidates[i], i, path); // 这里i不用+1，重复利用
                path.pollLast();
            }
        }

    }

}
