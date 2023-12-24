package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _226invertTree {

    public static class Solution1 {

        /**
         DFS-递归：前序/后序都可以，中序不行
         时间复杂度：O(N)
         空间复杂度：O(H)
         */
        public TreeNode invertTree(TreeNode root) {
            dfs(root);
            return root;
        }

        private void dfs(TreeNode root) {
            if (root == null) return;

            // 翻转当前节点（可以放在翻转左右节点之前，也可以放在翻转左右节点之后，但不能放在翻转左右节点之间）
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            // 翻转左右子节点
            dfs(root.left);
            dfs(root.right);
        }

    }



    public static class Solution2 {

    }



    public static class Solution3 {

    }

}
