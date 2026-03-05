package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class _865subtreeWithAllDeepest {

    public static class Solution1 {

        /**
         DFS: O(N), O(N)
         Note: 先找到所有最深节点(DFS or BFS都可以)，然后找这些节点的LCA(DFS)[1644]。
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
         DFS(postorder): O(N), O(N)
         Note: 遍历的同时计算深度并做取舍
         */
        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            return dfs(root).getKey();
        }

        // 返回以当前节点为根节点的具有最深节点的最小子树的根节点(key)和最深深度(value)
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
