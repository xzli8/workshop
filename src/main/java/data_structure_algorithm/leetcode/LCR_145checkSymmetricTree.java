package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class LCR_145checkSymmetricTree {

    public static class Solution1 {

        /**
         DFS
         TimeComplexity: O(N)
         SpaceComplexity: O(N)
         */
        public boolean checkSymmetricTree(TreeNode root) {
            return isSymmetric(root, root);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            if (left == null && right == null) return true;
            if (left == null || right == null) return false;
            return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
        }

    }

}
