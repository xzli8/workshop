package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _1644lowestCommonAncestor {

    /**
     * 题解：
     *      https://blog.csdn.net/fdl123456/article/details/123887678
     *      https://www.cnblogs.com/Dylan-Java-NYC/p/16184297.html
     */

    public static class Solution1 {

        /**
         DFS(postorder)：O(N), O(N)
         Note: 如果两个节点未必在树中，一定要后序遍历
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
         *  DFS(postorder): O(N), O(N)
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
