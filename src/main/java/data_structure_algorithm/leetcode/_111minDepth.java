package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _111minDepth {

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val,TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }



    public static class Solution1 {


        /**
         递归（深度优先搜索）
         递推：
         当节点的左子节点为null，右子节点不为null时，返回右子节点的最小深度+1；
         当节点的右子节点为null，左子节点不为null时，返回左子节点的最小深度+1；
         当节点的左右子节点都不为null，返回左右子节点的最小深度中的最小值+1；
         终止：
         节点为null时，其深度为0
         节点为叶子结点(左右子节点均为null的节点为叶子结点)时，其深度为1
         时间复杂度：O(n)
         空间复杂度：平均O(logn),最坏O(n)
         */
         public int minDepth(TreeNode root) {
             if (null == root) return 0;
             if (null == root.left && null == root.right) return 1;
             if (null == root.left) return minDepth(root.right)+1;
             if (null == root.right) return minDepth(root.left)+1;
             return Math.min(minDepth(root.left), minDepth(root.right))+1;
         }

    }



    public static class Solution2 {

        /**
         层序遍历（广度优先搜索）
         */
        public int minDepth(TreeNode root) {
            if (null == root) return 0;

            int depth = 0;
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);
            while (!q.isEmpty()) {
                depth++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = q.poll();
                    if (node.left == null && node.right == null) return depth;
                    if (node.left != null) q.offer(node.left);
                    if (node.right != null) q.offer(node.right);
                }
            }
            return depth;
        }


    }



}
