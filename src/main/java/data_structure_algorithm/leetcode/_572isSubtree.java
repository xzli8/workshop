package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _572isSubtree {

    public static class Solution1 {

        /**
         DFS(类似题："1367.二叉树中的链表")：一个树是另一个树的子树的三种情况：和另一个树相同，是另一个树的左（右）子树的子树
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (root == null && subRoot == null) return true;
            if (root == null || subRoot == null) return false;
            return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        private boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

    }

}
