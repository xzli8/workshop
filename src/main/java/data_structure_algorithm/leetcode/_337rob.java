package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _337rob {

    public static class Solution1 {

        /**
         深度优先搜索
         思路：每个节点有两种选择：偷或者不偷，如果偷，那么不能偷左右子节点。
         递归遍历时，用map记录已经遍历过节点的最大值
         时间复杂度：O(n)
         空间复杂度：O(n)
         */
        public int rob(TreeNode root) {
            return tryRob(root);
        }

        private Map<TreeNode, Integer> map = new HashMap<>();
        private int tryRob(TreeNode root) {
            if (root == null) return 0;
            if (map.containsKey(root)) return map.get(root);

            // 偷当前节点，不能偷左右子节点，但可以偷左右子节点的左右子节点
            int sum1 = root.val;
            if (root.left != null) sum1 += tryRob(root.left.left) + tryRob(root.left.right);
            if (root.right != null) sum1 += tryRob(root.right.left) + tryRob(root.right.right);

            // 不偷当前节点
            int sum2 = tryRob(root.left) + tryRob(root.right);

            // 将两种选择得到的较大值放入map
            int sum = Math.max(sum1, sum2);
            map.put(root, sum);
            return sum;
        }


    }

}
