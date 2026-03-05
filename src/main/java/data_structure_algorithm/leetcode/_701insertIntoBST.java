package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _701insertIntoBST {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (val < root.val) root.left = insertIntoBST(root.left, val);
            else root.right = insertIntoBST(root.right, val);
            return root;
        }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public TreeNode insertIntoBST(TreeNode root, int val) {
             if (root == null) return new TreeNode(val);
             TreeNode p = root;
             while (true) {
                 if (val < p.val) {
                     if (p.left == null) {
                         p.left = new TreeNode(val);
                         return root;
                     } else {
                         p = p.left;
                     }
                 } else {
                     if (p.right == null) {
                         p.right = new TreeNode(val);
                         return root;
                     } else {
                         p = p.right;
                     }
                 }
             }
         }

    }

}
