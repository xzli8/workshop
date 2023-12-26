package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _108sortedArrayToBST {

    public static class Solution1 {

        /**
         DFS：为了保证平衡性，每次二分数组，将中点作为根节点建立二叉树
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode sortedArrayToBST(int[] nums) {
            return dfs(nums, 0, nums.length - 1);
        }

        private TreeNode dfs(int[] nums, int left, int right) {
            if (left > right) return null;
            int mid = left + ((right - left) >> 1);
            TreeNode root = new TreeNode(nums[mid]);
            root.left = dfs(nums, left, mid - 1);
            root.right = dfs(nums, mid + 1, right);
            return root;
        }

    }

}
