package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _623addOneRow {

    public static class Solution1 {

        /**
         BFS: Level order traverse
         TC: O(N)
         SC: O(N)
         */
        public TreeNode addOneRow(TreeNode root, int val, int depth) {
            if (depth == 1) {
                return new TreeNode(val, root, null);
            }

            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty() && depth > 1) {
                depth--;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (depth == 1) {
                        TreeNode left = node.left, right = node.right;
                        node.left = new TreeNode(val, left, null);
                        node.right = new TreeNode(val, null, right);
                    }
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
            }
            return root;
        }

    }

}
