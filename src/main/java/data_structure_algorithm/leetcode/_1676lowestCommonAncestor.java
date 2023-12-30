package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class _1676lowestCommonAncestor {

    /**
     * 输入一棵不含重复值的二叉树，但这次不是给你输入p和q两个节点了，而是给你输入一个包含若干节点的列表nodes（这些节点都存在于二叉树中），
     * 让你算这些节点的最近公共祖先。
     */

    public static class Solution1 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            return find(root, Arrays.stream(nodes).collect(Collectors.toSet()));
        }

        private TreeNode find(TreeNode cur, Set<TreeNode> nodes) {
            if (cur == null) return null;
            if (nodes.contains(cur)) return cur;
            TreeNode left = find(cur.left, nodes), right = find(cur.right, nodes);
            if (left == null) return right;
            if (right == null) return left;
            return cur;
        }

    }



    public static class Solution2 {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
            return find(root, Arrays.stream(nodes).map(TreeNode::getVal).collect(Collectors.toSet()));
        }

        private TreeNode find(TreeNode cur, Set<Integer> values) {
            if (cur == null) return null;
            if (values.contains(cur.val)) return cur;
            TreeNode left = find(cur.left, values), right = find(cur.right, values);
            if (left == null) return right;
            if (right == null) return left;
            return cur;
        }

    }

}
