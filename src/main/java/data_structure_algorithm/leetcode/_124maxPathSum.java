package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _124maxPathSum {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        private int maxSum = Integer.MIN_VALUE;

        // 计算以node为起点的最大路径和，并同时更新最大和
        private int maxGain(TreeNode node) {
            if (null == node) return 0;
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);
            maxSum = Math.max(maxSum, leftGain + rightGain + node.val);
            return Math.max(leftGain, rightGain) + node.val;
        }


    }

}
