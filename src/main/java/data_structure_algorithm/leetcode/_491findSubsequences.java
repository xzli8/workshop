package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _491findSubsequences {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：
         空间复杂度：
         */
        public List<List<Integer>> findSubsequences(int[] nums) {
            backtrace(nums, 0, new ArrayList<>());
            return res;
        }

        private List<List<Integer>> res = new ArrayList<>();
        private void backtrace(int[] nums, int startIndex, List<Integer> path) {
            if (path.size() > 1) {
                res.add(new ArrayList<>(path));
                // 这里不要return，因为要找树上的所有节点，与子集相同
            }

            Set<Integer> used = new HashSet<>();
            for (int i = startIndex; i < nums.length; i++) {
                if ((!path.isEmpty() && nums[i] < path.get(path.size() - 1)) || used.contains(nums[i])) {
                    continue;
                }
                used.add(nums[i]);
                path.add(nums[i]);
                backtrace(nums, i + 1, path);
                path.remove(path.size() - 1);
            }
        }


    }

}
