package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class _662widthOfBinaryTree {

    public static class Solution1 {

        /**
         广度优先搜索 + 创建虚拟节点
         创建虚拟节点，可以满足逻辑，但由于需要创建大量虚拟节点，且层级越深虚拟节点越多，会超过内存或时间限制
         */
         public int widthOfBinaryTree(TreeNode root) {
             int maxWidth = 0;
             Queue<TreeNode> q = new LinkedList<>();
             q.offer(root);
             while (!q.isEmpty()) {
                 int size = q.size();
                 int start = size, end = 0;
                 for (int i = 0; i < size; i++) {
                     TreeNode node = q.poll();
                     if (node.val <= 100) {
                         start = Math.min(start, i);
                         end = Math.max(end, i);
                     }

                     // 用val = 101表示空节点
                     if (node.left == null) node.left = new TreeNode(101);
                     if (node.right == null) node.right = new TreeNode(101);
                     q.offer(node.left);
                     q.offer(node.right);
                 }
                 if (start == size) break;   // 当全部为空节点时，start, end都没有被更新，此时跳出循环
                 maxWidth = Math.max(maxWidth, end - start + 1);
             }
             return maxWidth;
         }

    }



    public static class Solution2 {

        /**
         广度优先搜索 + 计算index
         对于完全二叉树：假如根节点的index为1，如果一个节点的index为i，那么它的左右子节点的编号为2i, 2i+1
         */
        public int widthOfBinaryTree(TreeNode root) {
            int maxWidth = 0;
            Pair<TreeNode, Integer> rootPair = new Pair<>(root, 1);
            Queue<Pair<TreeNode, Integer>> q = new LinkedList<>();
            q.offer(rootPair);
            while (!q.isEmpty()) {
                int size = q.size();
                // int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
                int start = -1, end = -1;
                for (int i = 0; i < size; i++) {
                    Pair<TreeNode, Integer> pair = q.poll();
                    TreeNode node = pair.getKey();
                    Integer index = pair.getValue();
                    if (start == -1) start = index;
                    end = index;
                    // start = Math.min(start, index);
                    // end = Math.max(end, index);

                    if (node.left != null) q.offer(new Pair<>(node.left, index * 2));
                    if (node.right != null) q.offer(new Pair<>(node.right, index * 2 + 1));
                }
                maxWidth = Math.max(maxWidth, end - start + 1);
            }
            return maxWidth;
        }

    }

}
