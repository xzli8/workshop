package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _2145reverseOddLevels {

    public static class Solution1 {

        /**
         DFS: O(N), O(logN)
         */
//        public TreeNode reverseOddLevels(TreeNode root) {
//            dfs(root, true);
//            return root;
//        }
//
//        private void dfs(TreeNode root, boolean isOdd) {
//            if (root == null) return;
//            if (isOdd) {
//                // 题目要求反转奇数层节点值，所以这里翻转节点不行(类似题: "226.翻转二叉树")
//                TreeNode left = root.left;
//                root.left = root.right;
//                root.right = left;
//            }
//            dfs(root.left, !isOdd);
//            dfs(root.right, !isOdd);
//        }

        /**
         DFS: O(N), O(logN)
         */
        public TreeNode reverseOddLevels(TreeNode root) {
            dfs(root.left, root.right, true);
            return root;
        }

        private void dfs(TreeNode root1, TreeNode root2, boolean isOdd) {
            if (root1 == null) return;
            if (isOdd) {
                int tmp = root1.val;
                root1.val = root2.val;
                root2.val = tmp;
            }
            dfs(root1.left, root2.right, !isOdd);
            dfs(root1.right, root2.left, !isOdd);
        }

    }


    public static class Solution2 {

        /**
         BFS: O(N), O(N)
         */
        public TreeNode reverseOddLevels(TreeNode root) {
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            boolean isOdd = false;
            while (!q.isEmpty()) {
                int size = q.size();
                List<TreeNode> nodes = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (isOdd) nodes.add(node);
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
                if (isOdd) {
                    for (int l = 0, r = size - 1; l < r; l++, r--) {
                        int tmp = nodes.get(l).val;
                        nodes.get(l).val = nodes.get(r).val;
                        nodes.get(r).val = tmp;
                    }
                }
                isOdd = !isOdd;
            }
            return root;
        }

    }



}
