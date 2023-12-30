package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _285inorderSuccessor {

    /**
     * 题目链接：https://leetcode.cn/problems/successor-lcci/
     */

    public static class Solution1 {

        /**
         中序遍历(递归)
         注意：不仅适用于BST，而且适用于所有二叉树，但没有充分利用BST的特性
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            dfs(root, p);
            return suc;
        }

        private TreeNode pre = null, suc = null;
        private void dfs(TreeNode cur, TreeNode p) {
            if (cur == null) return;
            dfs(cur.left, p);
            if (pre == p) suc = cur;
            pre = cur;
            dfs(cur.right, p);
        }

    }



    public static class Solution2 {

        /**
         中序遍历(迭代)
         注意：不仅适用于BST，而且适用于所有二叉树，但没有充分利用BST的特性
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
             Deque<TreeNode> s = new ArrayDeque<>();
             TreeNode prev = null, cur = root;
             while (!s.isEmpty() || cur != null) {
                 if (cur != null) {
                     s.push(cur);
                     cur = cur.left;
                 } else {
                     cur = s.pop();
                     if (prev == p) {
                         return cur;
                     }
                     prev = cur;
                     cur = cur.right;
                 }
             }
             return null;
         }

    }



    public static class Solution3 {

        /**
         利用BST的特性
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (p.right != null) {
                TreeNode successor = p.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                return successor;
            }

            TreeNode cur = root, successor = null;
            while (cur != null) {
                if (cur.val > p.val) {
                    successor = cur;
                    cur = cur.left;
                } else {
                    cur = cur.right;
                }
            }
            return successor;
        }

    }

}
