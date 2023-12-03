package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _90subsetsWithDup {

    public static class Solution1 {

        /**
         回溯
         思路：因为集合与元素顺序无关，并且数组中有重复元素，为了保证子集不重复，需要保证同一层不能选相同的元素
         做法：1.排序去重；2.set去重；
         时间复杂度：O(2^N)，每种元素有选/不选两种选择，一共有O(N)个元素，所有共有O(2^N)种结果
         空间复杂度：O(N)，递归栈深度为N，临时数组大小为N
         */
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // 排序
            Arrays.sort(nums);

            // 回溯
            backtrace(nums, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] nums, int start, LinkedList<Integer> path) {
            // 这里没有递归终止条件，每一种结果都要
            res.add(new LinkedList<>(path));

            for (int i = start; i < nums.length; i++) {
                // 去重
                if (i > start && nums[i] == nums[i - 1]) continue;

                path.addLast(nums[i]);
                backtrace(nums, i + 1, path);
                path.removeLast();
            }
        }

    }



    public static class Solution2 {

        /**
         2.set去重
         */
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            // 排序
            Arrays.sort(nums);

            // 回溯
            backtrace(nums, 0, new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] nums, int start, LinkedList<Integer> path) {
            res.add(new LinkedList<>(path));

            Set<Integer> set = new HashSet<>();
            for (int i = start; i < nums.length; i++) {
                if (set.contains(nums[i])) continue;
                set.add(nums[i]);
                path.addLast(nums[i]);
                backtrace(nums, i + 1, path);
                path.removeLast();
            }
        }

    }

}
