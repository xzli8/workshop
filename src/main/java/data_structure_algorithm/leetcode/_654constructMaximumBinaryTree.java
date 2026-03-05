package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _654constructMaximumBinaryTree {

    public static class Solution1 {

        /**
         DFS: O(N^2)[每次找最大值O(N)，构建O(N)，嵌套在一起O(N^2)], O(N)
         Note: 每次都先从序列中找最大值将序列一分为二，对应左右子树
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


    public static class Solution2 {

        // 单调栈(todo)
    }

}
