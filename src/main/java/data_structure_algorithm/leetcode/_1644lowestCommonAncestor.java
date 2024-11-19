package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _1644lowestCommonAncestor {

    /**
     * 题解：
     *      https://blog.csdn.net/fdl123456/article/details/123887678
     *      https://www.cnblogs.com/Dylan-Java-NYC/p/16184297.html
     *
     *      Solution1: 两次遍历，第一次找P和Q是否都存在，第二次找LCA(236)
     *      Solution2: 一次遍历
     */

    public static class Solution1 {

        /**
         DFS：(1644.二叉树的最近公共祖先II)如果两个节点未必在树中的做法
         时间复杂度：O(N)
         空间复杂度：O(H)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res = find(root, p, q);
            return count > 1 ? res : null;
        }

        private int count = 0;
        private TreeNode find(TreeNode cur, TreeNode p, TreeNode q) {
            if (cur == null) return null;
            TreeNode left = find(cur.left, p, q), right = find(cur.right, p, q);
            // 因为p和q未必在树中，所以这里一定要用后序遍历，保证所有节点都被遍历到
            // 同时需要进行计数，当p和q没有都被访问时，返回null
            if (cur.val == p.val || cur.val == q.val) {
                count++;
                return cur;
            }
            if (left == null) return right;
            if (right == null) return left;
            return cur;
        }

    }



    public static class Solution2 {

        /**
         *  DFS: Postorder
         *  TC: O(N)
         *  SC: O(N)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            TreeNode res = dfs(root, p, q);
            return (foundP && foundQ) ? res : null;
        }

        private boolean foundP = false, foundQ = false;
        private TreeNode dfs(TreeNode node, TreeNode p, TreeNode q) {
            if (node == null) return null;
            TreeNode left = dfs(node.left, p, q), right = dfs(node.right, p, q);
            if (node == p || node == q) {
                if (node == p) foundP = true;
                if (node == q) foundQ = true;
                return node;
            }
            if (left == null) return right;
            if (right == null)  return left;
            return node;
        }

    }

}
