package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class _865subtreeWithAllDeepest {

    public static class Solution1 {

        /**
         DFS: (DFS + 1644)
         TC: O(N)
         SC: O(N)
         */
         public TreeNode subtreeWithAllDeepest(TreeNode root) {
             findDeepestNodes(root, 0);
             return findLCA(root, deepestNodes);
         }

         private int maxDepth = -1;
         private Set<TreeNode> deepestNodes = new HashSet<>();
         private void findDeepestNodes(TreeNode node, int depth) {
             if (node == null) return;
             if (depth > maxDepth) {
                 maxDepth = depth;
                 deepestNodes.clear();
                 deepestNodes.add(node);
             } else if (depth == maxDepth) {
                 deepestNodes.add(node);
             }
             findDeepestNodes(node.left, depth + 1);
             findDeepestNodes(node.right, depth + 1);
         }

         private TreeNode findLCA(TreeNode root, Set<TreeNode> s) {
             if (root == null) return null;
             if (s.contains(root)) return root;
             TreeNode left = findLCA(root.left, s), right = findLCA(root.right, s);
             if (left == null) return right;
             if (right == null) return left;
             return root;
         }

    }



    public static class Solution2 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            return dfs(root).getKey();
        }

        private Pair<TreeNode, Integer> dfs(TreeNode root) {
            if (root == null) return new Pair<>(root, 0);
            Pair<TreeNode, Integer> left = dfs(root.left), right = dfs(root.right);
            if (left.getValue() > right.getValue()) {
                return new Pair<>(left.getKey(), left.getValue() + 1);
            }
            if (left.getValue() < right.getValue()) {
                return new Pair<>(right.getKey(), right.getValue() + 1);
            }
            return new Pair<>(root, left.getValue() + 1);
        }

    }

}
