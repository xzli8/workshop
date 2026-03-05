package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _270closestValue {

    /**
     * Ref: https://leetcode.doocs.org/lc/270/
     *      https://www.cnblogs.com/grandyang/p/5237170.html
     */

    public static class Solution1 {

        /**
         * DFS
         * Note: 通过比较当前节点的值与目标值的差的绝对值，来更新答案，如果目标值小于当前节点的值，我们就递归地搜索左子树，否则我们递归地搜索右子树
         */
        public int closestValue(TreeNode root, double target) {
            dfs(root, target);
            return res;
        }

        private int res;
        private double minDiffAbs = Double.MAX_VALUE;
        private void dfs(TreeNode root, double target) {
            if (root == null) return;
            double diffAbs = Math.abs(root.val - target);
            if (diffAbs < minDiffAbs || (diffAbs == minDiffAbs && root.val < res) ) {
                res = root.val;
                minDiffAbs = diffAbs;
            }
            if (root.val < target) dfs(root.right, target);
            else dfs(root.left, target);
        }

    }

}
