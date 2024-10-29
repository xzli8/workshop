package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _1448goodNodes {

    public static class Solution1 {

        /**
         DFS: preorder
         */
        public int goodNodes(TreeNode root) {
            return dfs(root, root.val);
        }

        private int dfs(TreeNode node, int pathMax) {
            if (node == null) return 0;
            int count = 0;
            if (node.val >= pathMax) {
                count++;
                pathMax = node.val;
            }
            count += dfs(node.left, pathMax) + dfs(node.right, pathMax);
            return count;
        }

    }

}
