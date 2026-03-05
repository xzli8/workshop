package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _235lowestCommonAncestor {

    public static class Solution1 {

        /**
         DFS: O(N), O(N)
         Note: BST与一般二叉树相比，能指明下一步搜索的方向是左子树还是右子树
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return dfs(root, Math.min(p.val, q.val), Math.max(p.val, q.val));
        }

        private TreeNode dfs(TreeNode root, int min, int max) {
            if (root.val < min) return dfs(root.right, min, max);
            if (root.val > max) return dfs(root.left, min, max);
            return root;
        }

    }

}
