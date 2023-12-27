package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _606tree2str {

    public static class Solution1 {

        /**
         DFS-递归
         思路：分为四种情况讨论，分别是当前节点是否有左右子节点
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String tree2str(TreeNode root) {
            dfs(root);
            return sb.toString().substring(1, sb.length() - 1);
        }

        private StringBuilder sb = new StringBuilder();
        private void dfs(TreeNode cur) {
            if (cur == null) return;
            sb.append("(").append(cur.val);
            if (cur.left == null && cur.right != null) {
                sb.append("()");
                dfs(cur.right);
            }
            else if (cur.left != null && cur.right == null) {
                dfs(cur.left);
            }
            else if (cur.left != null && cur.right != null) {
                dfs(cur.left);
                dfs(cur.right);
            }
            // cur.left == null && cur.right == null
            sb.append(")");
        }

    }

}
