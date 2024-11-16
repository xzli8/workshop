package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _671findSecondMinimumValue {

    public static class Solution1 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public int findSecondMinimumValue(TreeNode root) {
            rootValue = root.val;
            dfs(root);
            return res;
        }

        private int rootValue;
        private int res = -1;
        private void dfs(TreeNode node) {
            if (node == null) return;
            if (node.val > rootValue) {
                if (res == -1) {
                    res = node.val;
                } else {
                    res = Math.min(res, node.val);
                }
            }
            dfs(node.left);
            dfs(node.right);
        }

    }

}
