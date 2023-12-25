package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _543diameterOfBinaryTree {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int diameterOfBinaryTree(TreeNode root) {
            dfs(root);
            return maxLen;
        }

        // 计算以当前节点为起点的最长路径，并更新全局最长路径
        private int maxLen = 0;
        private int dfs(TreeNode cur) {
            if (cur == null) return 0;
            int leftLen = dfs(cur.left), rightLen = dfs(cur.right);
            maxLen = Math.max(maxLen, leftLen + rightLen);
            return Math.max(leftLen, rightLen) + 1;
        }

    }

}
