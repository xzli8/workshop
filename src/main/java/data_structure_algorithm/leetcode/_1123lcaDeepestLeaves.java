package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

public class _1123lcaDeepestLeaves {

    public static class Solution1 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            return dfs(root).getKey();
        }

        private Pair<TreeNode, Integer> dfs(TreeNode root) {
            if (root == null) return new Pair<>(root, 0);
            Pair<TreeNode, Integer> left = dfs(root.left), right = dfs(root.right);
            if (left.getValue() > right.getValue()) {
                return new Pair<>(left.getKey(), left.getValue() + 1);
            }
            if (left.getValue() < right.getValue()) {
                return new Pair<>(right.getKey(), right.getValue() + 1);
            }
            return new Pair<>(root, left.getValue() + 1);
        }

    }

}
