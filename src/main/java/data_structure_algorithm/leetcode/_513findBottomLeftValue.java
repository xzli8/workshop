package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _513findBottomLeftValue {

    public class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int findBottomLeftValue(TreeNode root) {
             int res = 0;
             Queue<TreeNode> q = new ArrayDeque<>();
             q.offer(root);
             while (!q.isEmpty()) {
                 int size = q.size();
                 for (int i = 0; i < size; i++) {
                     TreeNode cur = q.poll();
                     if (cur.left != null) q.offer(cur.left);
                     if (cur.right != null) q.offer(cur.right);
                     if (i == 0) res = cur.val;
                 }
             }
             return res;
         }

    }



    public class Solution2 {

        /**
         DFS-v1：进入下一层递归前不判空，在递归开始时判空
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int findBottomLeftValue(TreeNode root) {
             dfs(root, 0);
             return res;
         }

         private int res = 0, maxDepth = Integer.MIN_VALUE;
         private void dfs(TreeNode cur, int depth) {
             if (cur == null) return;
             if (cur.left == null && cur.right == null) {
                 if (depth > maxDepth) {
                     maxDepth = depth;
                     res = cur.val;
                 }
                 return;
             }
             dfs(cur.left, depth + 1);
             dfs(cur.right, depth + 1);
         }

    }



    public class Solution3 {

        /**
         DFS-v2：在进入下一层递归前判空，递归开始时不判空
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int findBottomLeftValue(TreeNode root) {
            dfs(root, 0);
            return res;
        }

        private int res = 0, maxDepth = Integer.MIN_VALUE;
        private void dfs(TreeNode cur, int depth) {
            if (cur.left == null && cur.right == null) {
                if (depth > maxDepth) {
                    maxDepth = depth;
                    res = cur.val;
                }
                return;
            }
            if (cur.left != null) dfs(cur.left, depth + 1);
            if (cur.right != null) dfs(cur.right, depth + 1);
        }

    }



}
