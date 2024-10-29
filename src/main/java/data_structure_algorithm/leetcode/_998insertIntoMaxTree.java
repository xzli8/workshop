package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _998insertIntoMaxTree {

    public static class Solution1 {

        /**
         遍历寻找右子节点
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            TreeNode node = new TreeNode(val), prev = null, cur = root;
            while (cur != null && cur.val > val) {
                prev = cur;
                cur = cur.right;
            }
            if (prev == null) {
                node.left = cur;
                return node;
            } else {
                prev.right = node;
                node.left = cur;
                return root;
            }
        }

    }

}
