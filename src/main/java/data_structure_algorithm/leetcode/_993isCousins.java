package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _993isCousins {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean isCousins(TreeNode root, int x, int y) {
             Queue<TreeNode> q = new ArrayDeque<>();
             q.offer(root);
             while (!q.isEmpty()) {
                 TreeNode xp = null, yp = null;
                 int size = q.size();
                 for (int i = 0; i < size; i++) {
                     TreeNode cur = q.poll();
                     if (cur.left != null) {
                         q.offer(cur.left);
                         if (cur.left.val == x) xp = cur;
                         if (cur.left.val == y) yp = cur;
                     }
                     if (cur.right != null) {
                         q.offer(cur.right);
                         if (cur.right.val == x) xp = cur;
                         if (cur.right.val == y) yp = cur;
                     }
                 }
                 if (xp != null && yp != null) return xp != yp;
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         DFS:深度遍历，记录x,y的深度与父节点
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        private int x, y;
        private int xdepth, ydepth;
        private TreeNode xp, yp;

        private void dfs(TreeNode node, int depth, TreeNode parent) {
            if (null == node) {
                return;
            }

            if (node.val == x) {
                xdepth = depth;
                xp = parent;
            }
            if (node.val == y) {
                ydepth = depth;
                yp = parent;
            }
            dfs(node.left, depth+1, node);
            dfs(node.right, depth+1, node);
        }

        public boolean isCousins(TreeNode root, int x, int y) {
            this.x = x;
            this.y = y;
            dfs(root, 0, null);
            return xdepth == ydepth && xp != yp;
        }

    }


}
