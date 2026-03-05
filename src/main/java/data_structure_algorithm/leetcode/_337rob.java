package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _337rob {

    public static class Solution1 {

        /**
         树形DP(DFS-postorder): O(N), O(N)
         Ref: https://leetcode.cn/problems/house-robber-iii/solutions/2282018/shi-pin-ru-he-si-kao-shu-xing-dppythonja-a7t1/
         */
        public int rob(TreeNode root) {
            int[] res = dfs(root);
            return Math.max(res[0], res[1]); // 根节点选或不选的最大值
        }

        private int[] dfs(TreeNode node) {
            if (node == null) return new int[]{0, 0}; // 没有节点，怎么选都是 0
            int[] left = dfs(node.left), right = dfs(node.right); // 递归左右子树
            int rob = left[1] + right[1] + node.val; // 选当前节点
            int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // 不选当前节点
            return new int[]{rob, notRob};
        }

    }


    public static class Solution2 {

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
