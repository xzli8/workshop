package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _110isBalanced {

    public static class Solution1 {

        /**
         DFS
         递推：当前节点的左右子树都是平衡二叉树 && 左右子树的高度差不超过1
         终止：null节点一定是平衡二叉树
         如何计算节点的深度：递归。
         递推：当前节点深度 = max(左子树深度，右子树深度)+1
         终止：null节点的深度为0
         tips：可以用map存储节点及其对应深度，避免重复遍历计算（map相当于一个备忘录）

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isBalanced(TreeNode root) {
            if (null == root) return true;
            return isBalanced(root.left) && isBalanced(root.right)
                    && Math.abs(depth(root.left) - depth(root.right)) <= 1;
        }

        // private int depth(TreeNode root) {
        //     if (root == null) return 0;
        //     return Math.max(depth(root.left), depth(root.right)) + 1;
        // }

        // 用map存储节点及其对应深度，避免重复遍历计算（map相当于一个备忘录）
        private Map<TreeNode, Integer> map = new HashMap<>();
        private int depth(TreeNode root) {
            if (root == null) return 0;

            // if (map.containsKey(root)) return map.get(root);
            // int depth = Math.max(depth(root.left), depth(root.right)) + 1;
            // map.put(root, depth);
            // return depth;

            return map.computeIfAbsent(root, node -> Math.max(depth(node.left), depth(node.right)) + 1);
        }



    }

}
