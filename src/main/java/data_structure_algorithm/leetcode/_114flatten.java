package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _114flatten {

    public static class Solution1 {

        /**
         迭代：原地操作
         参考题解：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void flatten(TreeNode root) {
            while (root != null) {
                // 当左子树不为空时
                if (root.left != null) {
                    // 找左子树的最右节点
                    TreeNode node = root.left;
                    while (node.right != null) {
                        node = node.right;
                    }

                    // 将右子树拼到左子树的最右节点下
                    node.right = root.right;
                    root.right = root.left;
                    root.left = null;
                }
                root = root.right;
            }
        }

    }

}
