package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _100isSameTree {

    public static class Solution1 {

        /**
         递归
         递推：根节点值相等，并且左右子树都是相同的树，那么这两棵树相同
         终止：节点为空
         */
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) return true;
            if (p == null || q == null) return false;
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

    }

}
