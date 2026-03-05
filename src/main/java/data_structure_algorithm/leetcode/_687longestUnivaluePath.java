package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _687longestUnivaluePath {

    /**
     * 参考题解：https://leetcode.cn/problems/longest-univalue-path/solutions/815692/yi-pian-wen-zhang-jie-jue-suo-you-er-cha-94j7/
     * NOTE：Java是值传递，但是传递的是变量的引用，所以会被一并修改；C++既可以值传递也可以引用传递，值传递的话就不用回溯了。
     */

    public static class Solution1 {

        /**
         DFS(postorder) + 贪心: O(N), O(N)
         */
        public int longestUnivaluePath(TreeNode root) {
            dfs(root);
            return maxLen;
        }

        private int maxLen = 0;

        // 计算以当前节点为起点的最长路径长度，同时更新最长路径长度
        private int dfs(TreeNode root) {
            if (root == null) return 0;

            // 先计算左右子树
            int leftLen = dfs(root.left), rightLen = dfs(root.right);

            // 再计算当前节点: 如果小于0就舍弃(贪心)
            int lenFromLeft = 0, lenFromRight = 0;
            if (root.left != null && root.left.val == root.val) {
                lenFromLeft = leftLen + 1;
            }
            if (root.right != null && root.right.val == root.val) {
                lenFromRight = rightLen + 1;
            }
            maxLen = Math.max(maxLen, lenFromLeft + lenFromRight);
            return Math.max(lenFromLeft, lenFromRight);
        }

    }

}
