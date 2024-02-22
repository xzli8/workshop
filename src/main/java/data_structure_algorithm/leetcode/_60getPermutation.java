package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _60getPermutation {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public String getPermutation(int n, int k) {
            this.n = n;
            this.k = k;
            nums = new int[n];
            for (int i = 0; i < n; i++) nums[i] = i+1;
            visited = new boolean[n];
            Arrays.fill(visited, false);

            backtrace(0, new StringBuilder());
            return res;
        }

        private int n;
        private int k;
        private int[] nums;
        private boolean[] visited;
        private String res;

        private void backtrace(int level, StringBuilder path) {
            if (level == n) {
                if (--k == 0) {
                    res = path.toString();
                }
                return;
            }

            for (int i = 0; i < n; i++) {
                if (k == 0) return;     // 剪枝
                if (visited[i]) continue;
                path.append(nums[i]);
                visited[i] = true;
                backtrace(level + 1, path);
                path.deleteCharAt(path.length() - 1);
                visited[i] = false;
            }
        }

    }

}
