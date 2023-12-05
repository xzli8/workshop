package data_structure_algorithm.leetcode;

public class _226invertTree {

     //Definition for a binary tree node.
     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode() {
         }

         TreeNode(int val) {
             this.val = val;
         }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }



    public static class Solution1 {

        /**
         深度优先搜索
         */
        public TreeNode invertTree(TreeNode root) {
            dfs(root);
            return root;
        }

        private void dfs(TreeNode root) {
            if (root == null) return;

            // 翻转当前节点
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;

            // 翻转左右子节点（可以放在翻转当前节点之前，也可以放在翻转当前节点之后）
            dfs(root.left);
            dfs(root.right);
        }


    }

}
