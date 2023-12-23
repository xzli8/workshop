package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _104maxDepth {


    public static class Solution1 {

        /**
         DFS
         递推：当前节点最大深度 = max(左子树最大深度，右子树最大深度) + 1
         终止：null节点的最大深度为0

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int maxDepth(TreeNode root) {
             if (null == root) return 0;
             return Math.max(maxDepth(root.left), maxDepth(root.right))+1;
         }

    }



    public static class Solution2 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxDepth(TreeNode root) {
            if (root == null) return 0;

            int depth = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                depth++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
            }
            return depth;
        }

    }



}
