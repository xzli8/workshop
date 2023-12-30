package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _669trimBST {

    public static class Solution1 {

        /**
         DFS-递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public TreeNode trimBST(TreeNode root, int low, int high) {
             if (root == null) return null;
             if (root.val < low) return trimBST(root.right, low, high);  // 左子树全部被砍掉
             if (root.val > high) return trimBST(root.left, low, high);  // 右子树全部被砍掉

             // root.val在low和high之间时，root可以被保留，递归处理其左右子树
             root.left = trimBST(root.left, low, high);
             root.right = trimBST(root.right, low, high);
             return root;
         }

    }



    public static class Solution2 {

        /**
         DFS-迭代
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public TreeNode trimBST(TreeNode root, int low, int high) {
            // 先定位到在[low, high]范围内的第一个节点，将不符合要求的子树砍掉
            while (root != null && (root.val < low || root.val > high)) {
                if (root.val < low) root = root.right;
                if (root.val > high) root = root.left;
            }

            // 砍掉root.left中小于low的节点，因为root.val > low，根据BST特性，root.right > low
            TreeNode cur = root;
            while (cur != null) {
                while (cur.left != null && cur.left.val < low) cur.left = cur.left.right;
                cur = cur.left;
            }

            // 砍掉root.right中大于high的节点，因为root.val < high，根据BST特性，root.left < high
            cur = root;
            while (cur != null) {
                while (cur.right != null && cur.right.val > high) cur.right = cur.right.left;
                cur = cur.right;
            }
            return root;
        }

    }

}
