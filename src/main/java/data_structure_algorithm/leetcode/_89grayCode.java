package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _89grayCode {

    public static class Solution1 {

        /**
         回溯：https://leetcode.cn/problems/gray-code/solutions/973900/hui-su-javadai-ma-zhu-shi-by-xiao-xiao-l-sz0r/
         时间复杂度：O(2^N)
         空间复杂度：O(N)
         */
        public List<Integer> grayCode(int n) {
            dfs(n, new StringBuilder(), new int[] {0, 1});
            return res;
        }

        private List<Integer> res = new ArrayList<>();
        private void dfs(int n, StringBuilder path, int[] nums) {
            if (path.length() == n) {
                res.add(Integer.valueOf(path.toString(), 2));   // 二进制转十进制
                return;
            }

            path.append(nums[0]);
            dfs(n, path, new int[] {0, 1});
            path.deleteCharAt(path.length() - 1);

            path.append(nums[1]);
            dfs(n, path, new int[] {1, 0});
            path.deleteCharAt(path.length() - 1);
        }

    }

}
