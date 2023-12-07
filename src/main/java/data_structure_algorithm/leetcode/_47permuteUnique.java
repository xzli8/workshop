package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _47permuteUnique {

    public static class Solution1 {

        /**
         回溯：在选择时，同一层相同元素的相对顺序要保持一致
         时间复杂度：O(N! * N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> permuteUnique(int[] nums) {
            Arrays.sort(nums);
            backtrace(nums, new boolean[nums.length], new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] nums, boolean[] visited, LinkedList<Integer> path) {
            if (path.size() == nums.length) {
                res.add(new LinkedList<>(path));
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                if (visited[i]) continue;
                if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;  // 去重
                // if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) continue;  // 也能去重，但效率更低
                visited[i] = true;
                path.addLast(nums[i]);
                backtrace(nums, visited, path);
                path.removeLast();
                visited[i] = false;
            }
        }

    }

}
