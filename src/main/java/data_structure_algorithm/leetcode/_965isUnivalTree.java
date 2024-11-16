package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _965isUnivalTree {

    public static class Solution1 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public boolean isUnivalTree(TreeNode root) {
            this.rootValue = root.val;
            return dfs(root);
        }

        private int rootValue;
        private boolean dfs(TreeNode node) {
            if (node == null) return true;
            return node.val == rootValue && dfs(node.left) && dfs(node.right);
        }

    }

}
