package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _687longestUnivaluePath {

    /**
     * 参考题解：https://leetcode.cn/problems/longest-univalue-path/solutions/815692/yi-pian-wen-zhang-jie-jue-suo-you-er-cha-94j7/
     * NOTE：Java是值传递，但是传递的是变量的引用，所以会被一并修改；C++既可以值传递也可以引用传递，值传递的话就不用回溯了。
     */

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return maxLen;
        }

        // 计算以cur为起点的最长路径长度，同时更新最长路径长度
        private int maxLen = 0;
        private int dfs(TreeNode cur) {
            if (cur == null) return 0;
            int leftLen = dfs(cur.left);
            int rightLen = dfs(cur.right);

            int lenFromLeft = 0, lenFromRight = 0;
            if (cur.left != null && cur.left.val == cur.val) {
                lenFromLeft = leftLen + 1;
            }
            if (cur.right != null && cur.right.val == cur.val) {
                lenFromRight = rightLen + 1;
            }
            maxLen = Math.max(maxLen, lenFromLeft + lenFromRight);
            return Math.max(lenFromLeft, lenFromRight);
        }

    }

}
