package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _938rangeSumBST {

    public static class Solution0 {

        /**
         DFS(postorder): O(N), O(N)
         Note: 利用BST的性质进行剪枝，效率高。
         ref:https://leetcode.cn/problems/range-sum-of-bst/solutions/6703/hua-jie-suan-fa-938-er-cha-sou-suo-shu-de-fan-wei-/?envType=daily-question&envId=2024-02-26
         */
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) return 0;
            if (root.val < low) return rangeSumBST(root.right, low, high);
            if (root.val > high) return rangeSumBST(root.left, low, high);
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }

    }


    public static class Solution1 {

        /**
         DFS: O(N), O(N)
         Note: 遍历所有节点逐个判断是否在范围内，适用于所有二叉树(没有利用BST性质，效率较低)
         */
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) return 0;
            int val = root.val >= low && root.val <= high ? root.val : 0;
            return val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }

    }


}
