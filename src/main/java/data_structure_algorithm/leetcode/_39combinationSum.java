package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _39combinationSum {

    public static class Solution1 {

        /**
         回溯：每次遍历下一层时，从当前元素开始，以满足“可重复选取”条件
         时间复杂度：O(S)，S为所有可行解的长度之和
         空间复杂度：O(target)
         */
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            backtrace(candidates, target, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] candidates, int target, int start, LinkedList<Integer> path) {
            if (target == 0) {
                res.add(new ArrayList<Integer>(path));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] < 0) continue;   // 剪枝
                path.addLast(candidates[i]);
                backtrace(candidates, target - candidates[i], i, path); // 因为可重复选取，这里要从i开始
                path.removeLast();
            }
        }

    }

}
