package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _814pruneTree {

    public static class Solution1 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public TreeNode pruneTree(TreeNode root) {
            return allZero(root) ? null : root;
        }

        private boolean allZero(TreeNode node) {
            if (node == null) return true;
            boolean leftAllZero = allZero(node.left), rightAllZero = allZero(node.right);
            if (leftAllZero) node.left = null;
            if (rightAllZero) node.right = null;
            return leftAllZero && rightAllZero && node.val == 0;
        }

    }

}
