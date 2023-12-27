package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _783minDiffInBST {

    public static class Solution1 {

        /**
         DFS：BST的中序遍历是升序序列，可以在中序遍历的同时，计算当前节点与中序遍历的前一个节点的差值，更新最小差值
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int minDiffInBST(TreeNode root) {
            dfs(root);
            return minDiff;
        }

        private TreeNode pre = null;
        private int minDiff = Integer.MAX_VALUE;

        private void dfs(TreeNode cur) {
            if (cur == null) return;
            dfs(cur.left);
            if (pre != null) {
                minDiff = Math.min(minDiff, cur.val - pre.val);
            }
            pre = cur;
            dfs(cur.right);
        }

    }

}
