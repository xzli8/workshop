package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class _1676lowestCommonAncestor {

    /**
     * Ref: https://leetcode.doocs.org/lc/1676/
     *
     * Des: 输入一棵不含重复值的二叉树，但这次不是给你输入p和q两个节点了，而是给你输入一个包含若干节点的列表nodes（这些节点都存在于二叉树中），
     * 让你算这些节点的最近公共祖先。
     */

    public static class Solution1 {

        /**
         * DFS(postorder): O(N), O(N)
         * Note: 给定一系列节点，可以用Set<node>判断。
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            return find(root, Arrays.stream(nodes).collect(Collectors.toSet()));
        }

        private TreeNode find(TreeNode root, Set<TreeNode> nodes) {
            if (root == null) return null;
            if (nodes.contains(root)) return root;
            TreeNode left = find(root.left, nodes), right = find(root.right, nodes);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }

    }



    public static class Solution2 {

        /**
         * DFS(postorder): O(N), O(N)
         * Note: 给定一系列节点，并且二叉树不含重复值，也可以用Set<value>判断。
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            return find(root, Arrays.stream(nodes).map(TreeNode::getVal).collect(Collectors.toSet()));
        }

        private TreeNode find(TreeNode root, Set<Integer> values) {
            if (root == null) return null;
            if (values.contains(root.val)) return root;
            TreeNode left = find(root.left, values), right = find(root.right, values);
            if (left == null) return right;
            if (right == null) return left;
            return root;
        }

    }

}
