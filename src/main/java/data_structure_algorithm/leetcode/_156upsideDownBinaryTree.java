package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _156upsideDownBinaryTree {

    /**
     * Lintcode: https://www.lintcode.com/problem/649/
     * Reference: https://www.acwing.com/solution/LeetCode/content/250/
     */

    public static class Solution1 {

        /**
         DFS
         */
        public TreeNode upsideDownBinaryTree(TreeNode root) {
            if (root == null || root.left == null) return root;

            TreeNode node = upsideDownBinaryTree(root.left);
            root.left.left = root.right;
            root.left.right = root;
            root.left = null;
            root.right = null;
            return node;
        }

    }

}
