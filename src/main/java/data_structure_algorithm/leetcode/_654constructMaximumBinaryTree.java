package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _654constructMaximumBinaryTree {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return build(nums, 0, nums.length - 1);
        }

        private TreeNode build(int[] nums, int left, int right) {
            if (left > right) return null;

            int maxVal = nums[left], maxIdx = left;
            for (int i = left; i <= right; i++) {
                if (nums[i] > maxVal) {
                    maxVal = nums[i];
                    maxIdx = i;
                }
            }

            TreeNode root = new TreeNode(maxVal);
            root.left = build(nums, left, maxIdx - 1);
            root.right = build(nums, maxIdx + 1, right);
            return root;
        }

    }

}
