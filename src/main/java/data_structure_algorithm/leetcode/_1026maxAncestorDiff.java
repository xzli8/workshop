package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

public class _1026maxAncestorDiff {

    public static class Solution1 {

        /**
         dfs(preorder): O(N), O(N)
         */
        public int maxAncestorDiff(TreeNode root) {
            dfs(root, root.val, root.val);
            return res;
        }

        private int res = Integer.MIN_VALUE;

        /**
         遍历到当前节点root时，其祖先节点的最小值为min最大值为max
         */
        private void dfs(TreeNode root, int min, int max) {
            if (root == null) return;
            res = Math.max(res, Math.max(Math.abs(root.val - min), Math.abs(root.val - max)));
            min = Math.min(min, root.val);
            max = Math.max(max, root.val);
            dfs(root.left, min, max);
            dfs(root.right, min, max);
        }

    }

}
