package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _617mergeTrees {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(M + N)
         空间复杂度：O(M + N)
         */
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;
            if (root2 == null) return root1;

            root1.val += root2.val;
            root1.left = mergeTrees(root1.left, root2.left);
            root1.right = mergeTrees(root1.right, root2.right);
            return root1;
        }

    }

}
