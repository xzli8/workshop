package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _700searchBST {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(logN)
         空间复杂度：O(logN)
         */
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) return null;
            if (val == root.val) return root;
            else if (val < root.val) return searchBST(root.left, val);
            else return searchBST(root.right, val);
        }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         public TreeNode searchBST(TreeNode root, int val) {
             while (root != null) {
                 if (val == root.val) return root;
                 else if (val < root.val) root = root.left;
                 else root = root.right;
             }
             return null;
         }

    }

}
