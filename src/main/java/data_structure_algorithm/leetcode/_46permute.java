package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _46permute {

    public static class Solution1 {

        /**
         回溯
         分析：不同与“子集/组合”，排列与顺序有关，相同的元素不同的顺序是不同的排列
         思路：不能像之前一样用"start"来去重，而要用一个visited数组来表示元素是否使用过

         时间复杂度：O(N! * N)，backtrace被调用次数为排列数A(N, N) = N!，每种结果需要O(N)的时间枚举
         空间复杂度：O(N)，递归深度为N，临时数组大小为N
         */
        public List<List<Integer>> permute(int[] nums) {
            backtrace(nums, new boolean[nums.length], new LinkedList<>());
            return res;
        }

        private final List<List<Integer>> res = new ArrayList<>();

        private void backtrace(int[] nums, boolean[] visited, LinkedList<Integer> path) {
            // 递归终止条件
            if (path.size() == nums.length) {
                res.add(new LinkedList<>(path));
                return;
            }

            // 这里每次需要从头开始遍历
            for (int i = 0; i < nums.length; i++) {
                // 元素已经被使用过，跳过
                if (visited[i]) continue;

                // 使用当前元素
                visited[i] = true;
                path.addLast(nums[i]);

                // 进入下一层
                backtrace(nums, visited, path);

                // 不用当前元素
                visited[i] = false;
                path.removeLast();
            }
        }

    }

}
