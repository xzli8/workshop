package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _938rangeSumBST {

    public static class Solution1 {

        /**
         DFS
         ref:https://leetcode.cn/problems/range-sum-of-bst/solutions/6703/hua-jie-suan-fa-938-er-cha-sou-suo-shu-de-fan-wei-/?envType=daily-question&envId=2024-02-26
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) return 0;
            if (root.val < low) return rangeSumBST(root.right, low, high);
            if (root.val > high) return rangeSumBST(root.left, low, high);
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }

    }

}
