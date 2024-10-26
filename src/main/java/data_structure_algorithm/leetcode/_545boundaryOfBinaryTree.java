package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _545boundaryOfBinaryTree {

    /**
     * Lintcode: https://www.lintcode.com/problem/878/
     */

    public static class Solution1 {

        /**
         preorder + postorder
         */
        public List<Integer> boundaryOfBinaryTree(TreeNode root) {
            if (root == null) return res;
            if (root.left == null && root.right == null) {
                res.add(root.val);
                return res;
            }

            res.add(root.val);
            getLeft(root.left);
            getLeaves(root);
            getRight(root.right);
            return res;
        }

        private List<Integer> res = new ArrayList<>();

        // preorder
        private void getLeft(TreeNode node) {
            if (node == null || (node.left == null && node.right == null)) return;
            res.add(node.val);
            if (node.left != null) {
                getLeft(node.left);
            } else {
                getLeft(node.right);
            }
        }

        private void getLeaves(TreeNode node) {
            if (node == null) return;
            if (node.left == null && node.right == null) {
                res.add(node.val);
            }
            getLeaves(node.left);
            getLeaves(node.right);
        }

        // postorder
        private void getRight(TreeNode node) {
            if (node == null || (node.left == null && node.right == null)) return;
            if (node.right != null) {
                getRight(node.right);
            } else {
                getRight(node.left);
            }
            res.add(node.val);
        }

    }

}
