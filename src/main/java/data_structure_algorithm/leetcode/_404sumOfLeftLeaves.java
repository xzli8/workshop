package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _404sumOfLeftLeaves {

    public class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int sumOfLeftLeaves(TreeNode root) {
            dfs(root);
            return sum;
        }

        private int sum = 0;
        private void dfs(TreeNode root) {
            // 可以在进入循环后再判空
            if (root == null) return;

            // 只能在父级节点才能判断是否是左叶子
            if (root.left != null && root.left.left == null && root.left.right == null) {
                sum += root.left.val;
                // 这里不能return，因为要遍历整棵树求所有左叶子结点之和
            }

            dfs(root.left);
            dfs(root.right);

            // 也可以在这里判空后再进入下一层循环
            // if (root.left != null) dfs(root.left);
            // if (root.right != null) dfs(root.right);
        }

    }

}
