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
            // 因为要反复删除，所以要先处理左右子节点再处理当前节点
            // 因为子节点删除后可能是null，所以这里要改变root.left/right的指向
            root.left = removeLeafNodes(root.left, target);
            root.right = removeLeafNodes(root.right, target);
            // 处理完左右子节点再处理当前节点
            if (root.left == null && root.right == null && root.val == target) {
                return null;
            }
            return root;
        }

    }

}
