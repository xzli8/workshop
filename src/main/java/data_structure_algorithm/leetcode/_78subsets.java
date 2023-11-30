package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _78subsets {

    public static class Solution1 {

        // 回溯
        public List<List<Integer>> subsets(int[] nums) {
            backtrace(nums, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int[] nums, int start, Deque<Integer> path) {
            // 这里判断递归结束条件，本题没有结束条件
            res.add(new ArrayList<>(path));

            for (int i = start; i < nums.length; i++) {
                path.offerLast(nums[i]);
                backtrace(nums, i + 1, path);
                path.pollLast();
            }
        }

    }

}
