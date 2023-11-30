package data_structure_algorithm.leetcode;

import java.util.*;

public class _90subsetsWithDup {

    public static class Solution1 {

        // 回溯 + 剪枝（将选择过的元素剔除）
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            backtrace(nums, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int[] nums, int start, Deque<Integer> path) {
            res.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {
                if (i > start && nums[i] == nums[i-1]) continue;    // 剪枝
                path.offerLast(nums[i]);
                backtrace(nums, i + 1, path);
                path.pollLast();
            }
        }

    }

}
