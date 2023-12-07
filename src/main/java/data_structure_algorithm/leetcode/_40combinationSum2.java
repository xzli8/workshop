package data_structure_algorithm.leetcode;

import java.util.*;

public class _40combinationSum2 {

    public static class Solution1 {

        /**
         回溯：在做选择时，同一层已经选择过的元素不能再次选择
         时间复杂度：O(2^N * N)，每个数都可以选或者不选两种选择，枚举完一个选择最多需要O(N)时间
         空间复杂度：O(N)
         */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            backtrace(candidates, target, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] candidates, int target, int start, LinkedList<Integer> path) {
            if (target == 0) {
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = start; i < candidates.length; i++) {
                if (target - candidates[i] < 0) continue;   // 剪枝
                if (i > start && candidates[i] == candidates[i - 1]) continue;  // 去重
                path.addLast(candidates[i]);
                backtrace(candidates, target - candidates[i], i + 1, path);
                path.removeLast();
            }
        }

    }

}
