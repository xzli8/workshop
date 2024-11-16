package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _2236checkTree {

    public static class Solution1 {

        public boolean checkTree(TreeNode root) {
            return root.val == root.left.val + root.right.val;
        }

    }

}
