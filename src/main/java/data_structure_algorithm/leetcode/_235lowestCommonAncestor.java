package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _235lowestCommonAncestor {

    public static class Solution0 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            return dfs(root, Math.min(p.val, q.val), Math.max(p.val, q.val));
        }

        private TreeNode dfs(TreeNode cur, int min, int max) {
            if (cur.val < min) return dfs(cur.right, min, max);
            if (cur.val > max) return dfs(cur.left, min, max);
            return cur;
        }

    }



    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            int min = Math.min(p.val, q.val), max = Math.max(p.val, q.val);
            if (root.val < min) {
                return lowestCommonAncestor(root.right, p, q);
            } else if (root.val > max) {
                return lowestCommonAncestor(root.left, p, q);
            } else {
                return root;
            }
        }

    }

}
