package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _235lowestCommonAncestor {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int min = Math.min(p.val, q.val), max = Math.max(p.val, q.val);
            if (root.val < min) {
                return lowestCommonAncestor(root.right, p, q);
            } else if (root.val > max) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return root;
            }
        }

    }

}
