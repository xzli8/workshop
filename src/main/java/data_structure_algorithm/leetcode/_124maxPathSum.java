package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _124maxPathSum {

    public static class Solution1 {

        /**
         DFS(postorder) + 贪心: O(N), O(N)
         */
        public int maxPathSum(TreeNode root) {
            dfs(root);
            return maxSum;
        }

        private int maxSum = Integer.MIN_VALUE;

        // 计算以node为起点的最大路径和，并同时更新最大和
        private int dfs(TreeNode node) {
            if (null == node) return 0;
            // 先计算左右子树，再计算当前节点：小于0就舍弃(贪心)
            int left = Math.max(dfs(node.left), 0), right = Math.max(dfs(node.right), 0);
            maxSum = Math.max(maxSum, left + right + node.val);
            return Math.max(left, right) + node.val;
        }


    }

}
