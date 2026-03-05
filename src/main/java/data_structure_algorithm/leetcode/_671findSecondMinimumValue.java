package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _671findSecondMinimumValue {


    public static class Solution1 {

        /**
         DFS: O(N), O(N)
         Note: 不太好，受到数据范围的限制，改进做法是Solution2
         */
        public int findSecondMinimumValue(TreeNode root) {
            dfs(root);
            return secondMin == Long.MAX_VALUE ? -1 : (int) secondMin;
        }

        private long firstMin = Long.MAX_VALUE, secondMin = Long.MAX_VALUE;
        private void dfs(TreeNode root) {
            if (root == null) return;

            // 前/中/后序遍历都可以
            if (root.val < firstMin) {
                secondMin = firstMin;
                firstMin = root.val;
            } else if (root.val > firstMin && root.val < secondMin) {
                secondMin = root.val;
            }
            dfs(root.left);
            dfs(root.right);
        }

    }

    public static class Solution2 {

        /**
         DFS: O(N), O(N)
         */
        public int findSecondMinimumValue(TreeNode root) {
            dfs(root);
            return secondMin;
        }

        private int firstMin = -1, secondMin = -1;
        private void dfs(TreeNode root) {
            if (root == null) return;

            // 前/中/后序遍历都可以
            if (firstMin == -1) {
                firstMin = root.val;
            } else if (root.val < firstMin) {
                secondMin = firstMin;
                firstMin = root.val;
            } else if (root.val > firstMin && (secondMin == -1 || root.val < secondMin)) {
                secondMin = root.val;
            }
            dfs(root.left);
            dfs(root.right);
        }

    }

}
