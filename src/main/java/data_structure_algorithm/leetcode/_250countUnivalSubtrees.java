package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _250countUnivalSubtrees {

    /**
     * 参考题解：https://www.cnblogs.com/grandyang/p/5206862.html
     */

    public static class Solution1 {

        /**
         * 对于以节点now为根节点的子树来说，如果其所有节点是同值的，那么其左右子树必然也是同值的，
         * 且这棵树根节点的值now.val必然等于now.left.val以及now.right.val，于是可以从root开始向下dfs。
         * 对于当前处理的子树now，先递归求解now.left和now.right，并使用两个变量left_flag和right_flag分别记录左右子树是否为同值子树。
         * 若now.val与now.left.val和now.right.val相等，且left_flag和right_flag均为真，那么说明以now为根节点的子树就是同值子树，
         * 此时更新答案然后返回true，否则返回false。
         */

        public int countUnivalSubtrees(TreeNode root) {
            dfs(root);
            return count;
        }

        private int count = 0;
        private boolean dfs(TreeNode cur) {
            if (cur == null) return true;
            // 左右子树不是同值子树 or 左右子节点的值和根节点不相等
            if (!dfs(cur.left) || !dfs(cur.right) || (cur.left != null && cur.left.val != cur.val) || (cur.right != null && cur.right.val != cur.val)) return false;
            count++;
            return true;
        }

    }


}
