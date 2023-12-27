package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _230kthSmallest {

    public static class Solution1 {

        /**
         DFS-递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int kthSmallest(TreeNode root, int k) {
             this.k = k;
             dfs(root);
             return res;
         }

         private int res, k;
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             dfs(cur.left);
             if (k == 0) return;
             if (--k == 0) res = cur.val;
             dfs(cur.right);
         }

    }



    public static class Solution2 {

        /**
         DFS-迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int kthSmallest(TreeNode root, int k) {
            Deque<TreeNode> s = new ArrayDeque<>();
            TreeNode cur = root;
            while (cur != null || !s.isEmpty()) {
                if (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                } else {
                    cur = s.pop();
                    if (--k == 0) return cur.val;
                    cur = cur.right;
                }
            }
            return 0;
        }

    }



    public static class Solution3 {

        /**
         进阶：如果二叉树会被频繁修改、查询，则需要将二叉树转换成平衡二叉树，参考"1382.将二叉搜索树变平衡"
         */

    }



}
