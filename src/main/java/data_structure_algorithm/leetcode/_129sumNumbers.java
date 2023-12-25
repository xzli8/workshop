package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _129sumNumbers {

    public class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int sumNumbers(TreeNode root) {
            dfs(root, 0);
            return totalSum;
        }

        private int totalSum = 0;
        private void dfs(TreeNode cur, int pathSum) {
            if (cur == null) return;
            pathSum = pathSum * 10 + cur.val;
            if (cur.left == null && cur.right == null) {
                totalSum += pathSum;
                return;
            }
            dfs(cur.left, pathSum);
            dfs(cur.right, pathSum);
        }

    }



    public class Solution2 {

    }

}
