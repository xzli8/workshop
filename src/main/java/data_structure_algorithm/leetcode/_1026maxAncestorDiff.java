package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

public class _1026maxAncestorDiff {

    public static class Solution1 {

        /**
         DFS: postorder
         TC: O(N)
         SC: O(N)
         */
        public int maxAncestorDiff(TreeNode root) {
            dfs(root);
            return res;
        }

        private int res = 0;
        private Pair<Integer, Integer> dfs(TreeNode node) {
            if (node == null) return null;
            Pair<Integer, Integer> left = dfs(node.left), right = dfs(node.right);
            int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE, curMin = node.val, curMax = node.val;
            if (left != null) {
                minValue = Math.min(minValue, left.getKey());
                maxValue = Math.max(maxValue, left.getValue());
            }
            if (right != null) {
                minValue = Math.min(minValue, right.getKey());
                maxValue = Math.max(maxValue, right.getValue());
            }
            if (minValue != Integer.MAX_VALUE) {
                curMin = Math.min(curMin, minValue);
                res = Math.max(res, Math.abs(node.val - minValue));
            }
            if (maxValue != Integer.MIN_VALUE) {
                curMax = Math.max(curMax, maxValue);
                res = Math.max(res, Math.abs(node.val - maxValue));
            }
            return new Pair<>(curMin, curMax);
        }

    }

}
