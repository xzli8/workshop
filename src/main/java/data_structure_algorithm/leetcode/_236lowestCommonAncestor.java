package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _236lowestCommonAncestor {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            // 因为两个节点都在树中，所以这个判断放在前序位置更好，能够更快地返回，避免遍历全部节点
            // 如果两个节点不一定都在树中，这个判断要放在后序位置，以保证遍历到所有节点(1644.二叉树的最近公共祖先II)
            if (root == p || root == q) return root;
            TreeNode left = lowestCommonAncestor(root.left, p, q), right = lowestCommonAncestor(root.right, p, q);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }

    }

}
