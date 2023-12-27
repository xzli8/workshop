package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1038bstToGst {

    public static class Solution1 {

        /**
         DFS-递归：遍历顺序改为"右根左"，这样就能从小到大遍历节点，然后累加赋值即可
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public TreeNode bstToGst(TreeNode root) {
             dfs(root);
             return root;
         }

         private int sum = 0;
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             dfs(cur.right);
             sum += cur.val;
             cur.val = sum;
             dfs(cur.left);
         }

    }



    public static class Solution2 {

        /**
         DFS-迭代：遍历顺序改为"右根左"，这样就能从小到大遍历节点，然后累加赋值即可
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode bstToGst(TreeNode root) {
            Deque<TreeNode> s = new ArrayDeque<>();
            TreeNode cur = root;
            int sum = 0;
            while (cur != null || !s.isEmpty()) {
                if (cur != null) {
                    s.push(cur);
                    cur = cur.right;
                } else {
                    cur = s.pop();
                    sum += cur.val;
                    cur.val = sum;
                    cur = cur.left;
                }
            }
            return root;
        }

    }

}
