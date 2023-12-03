package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _78subsets {

    public static class Solution1 {

        /**
         回溯
         分析：集合中元素没有顺序，已加入元素不能重复加入。
         做法：通过起始下标来避免枚举已经枚举过的元素。

         时间复杂度：O(2^N)，每个元素都有选/不选两个选择，一共N个元素，共有2^N种选择
         空间复杂度：O(N)，递归深度为N，存放临时结果的path也需要O(N)的空间
         */
        public List<List<Integer>> subsets(int[] nums) {
            backtrace(nums, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] nums, int start, LinkedList<Integer> path) {
            // 这里没有递归终止条件，所有的结果都需要
            // 注意这里要new一个list加到最终结果中
            res.add(new LinkedList<>(path));

            // 遍历这一层所有的节点
            for (int i = start; i < nums.length; i++) {
                // 选择当前节点
                path.addLast(nums[i]);
                // 进入下一层(注意"i+1"，避免重复选择)
                backtrace(nums, i + 1, path);
                // 回到当前层，不选择当前节点
                path.removeLast();
            }
        }

    }

}
