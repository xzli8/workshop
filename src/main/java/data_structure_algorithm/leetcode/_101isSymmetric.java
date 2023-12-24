package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _101isSymmetric {


    public static class Solution1 {

        /**
         DFS
         递推：比较两棵树t1, t2是否对称，先要比较t1.val和t2.val是否相等，
         然后要递归比较t1.left和t2.right，t1.right和t2.left是否对称
         终止：t1或者t2为null，t1.val不等于t2.val

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean isSymmetric(TreeNode root) {
             return dfs(root, root);
         }

         private boolean dfs(TreeNode t1, TreeNode t2) {
             if (t1 == null && t2 == null) return true;
             if (t1 == null || t2 == null) return false;
             if (t1.val != t2.val) return false;
             return dfs(t1.left, t2.right) && dfs(t1.right, t2.left);
         }

    }



    public static class Solution2 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isSymmetric(TreeNode root) {
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode t1 = q.poll();
                TreeNode t2 = q.poll();
                if (t1 == null && t2 == null) continue; // t1, t2同时为null，继续循环，比较余下的节点
                if (t1 == null || t2 == null) return false; // t1, t2中有一个为null，返回false
                if (t1.val != t2.val) return false; // t1, t2的值不相等，返回false

                // 将t1, t2的左右子节点按照弹出时比较的顺序（即t1.left和t2.right比较，t1.right和t2.left比较）压入队列
                q.offer(t1.left);
                q.offer(t2.right);
                q.offer(t1.right);
                q.offer(t2.left);
            }
            return true;
        }

    }

}
