package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _1325removeLeafNodes {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public TreeNode removeLeafNodes(TreeNode root, int target) {
             TreeNode dummy = new TreeNode();
             dummy.left = root;
             do {
                 flag = false;
                 dfs(dummy, target);
             } while (flag);
             return dummy.left;
         }

         private boolean flag;
         private void dfs(TreeNode cur, int target) {
             if (cur.left != null && cur.left.left == null && cur.left.right == null
             && cur.left.val == target) {
                 flag = true;
                 cur.left = null;
             }
             if (cur.right != null && cur.right.left == null && cur.right.right == null
             && cur.right.val == target) {
                 flag = true;
                 cur.right = null;
             }

             if (cur.left != null) dfs(cur.left, target);
             if (cur.right != null) dfs(cur.right, target);
         }

    }



    public static class Solution2 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode removeLeafNodes(TreeNode root, int target) {
            if (root == null) return null;
            root.left = removeLeafNodes(root.left, target);
            root.right = removeLeafNodes(root.right, target);
            if (root.left == null && root.right == null && root.val == target) {
                return null;
            }
            return root;
        }

    }

}
