package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _563findTilt {

    public static class Solution1 {

        /**
         DFS: post order
         TC: O(N)
         SC: O(N)
         */
        public int findTilt(TreeNode root) {
            sum(root);
            return res;
        }

        private int res = 0;
        private int sum(TreeNode node) {
            if (node == null) return 0;
            int leftSum = sum(node.left), rightSum = sum(node.right);
            res += Math.abs(leftSum - rightSum);
            return leftSum + rightSum + node.val;
        }

    }

}
