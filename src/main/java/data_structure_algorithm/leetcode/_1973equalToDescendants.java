package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _1973equalToDescendants {

    /**
     * ref:https://www.cnblogs.com/cnoodle/p/15249586.html
     */

    public static class Solution1 {

        public int equalToDescendants(TreeNode root) {
            sum(root);
            return count;
        }

        private int count = 0;
        private int sum(TreeNode node) {
            if (node == null) return 0;
            int desSum = sum(node.left) + sum(node.right);
            if (node.val == desSum) count++;
            return desSum + node.val;
        }

    }

}
