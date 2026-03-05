package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _285inorderSuccessor {

    /**
     * 题目链接：https://leetcode.cn/problems/successor-lcci/
     * Ref: https://leetcode.doocs.org/lc/285/
     */

    public static class Solution0 {

        /**
         * 二分搜索: O(N), O(N)
         * Note: 二叉搜索树节点p的中序遍历后序节点是所有大于p的节点中的最小值。
         */
        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            TreeNode res = null;
            while (root != null) {
                if (root.val > p.val) {
                    res = root; // 先找到一个可能的值，后面可能再更新
                    root = root.left;
                } else {
                    root = root.right;
                }
            }
            return res;
        }

    }


    public static class Solution1 {

        /**
         中序遍历(递归)
         注意：不仅适用于BST，而且适用于所有二叉树，但没有充分利用BST的特性。还可以用Morris遍历(空间复杂度O(1))
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

}
