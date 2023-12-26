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
             TreeNode cur = root, parent = root;
             while (cur != null) {
                 if (val < cur.val) {
                     if (cur.left == null) {
                         cur.left = new TreeNode(val);
                         return root;
                     } else {
                         cur = cur.left;
                     }
                 } else {
                     if (cur.right == null) {
                         cur.right = new TreeNode(val);
                         return root;
                     } else {
                         cur = cur.right;
                     }
                 }
             }
             return root;
         }

    }

}
