package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _530getMinimumDifference {

    public static class Solution1 {

        /**
         DFS：将BST转换为有序数组，然后遍历有序数组，计算最小差值
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int getMinimumDifference(TreeNode root) {
             // 中序遍历得到升序序列
             dfs(root);

             // 遍历中序序列，计算最小差值
             int minDiff = Integer.MAX_VALUE;
             for (int i = 1; i < nums.size(); i++) {
                 minDiff = Math.min(minDiff, nums.get(i) - nums.get(i - 1));
             }
             return minDiff;
         }

         private List<Integer> nums = new ArrayList<>();
         private void dfs(TreeNode cur) {
             if (cur == null) return;
             dfs(cur.left);
             nums.add(cur.val);
             dfs(cur.right);
         }

    }



    public static class Solution2 {

        /**
         DFS：遍历过程中计算并更新最小差值，不需要额外空间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int getMinimumDifference(TreeNode root) {
            dfs(root);
            return minDiff;
        }

        private int minDiff = Integer.MAX_VALUE;
        private TreeNode prev = null;

        private void dfs(TreeNode cur) {
            if (cur == null) return;
            dfs(cur.left);
            if (prev != null) {
                minDiff = Math.min(minDiff, cur.val - prev.val);
            }
            prev = cur;
            dfs(cur.right);
        }


    }

}
