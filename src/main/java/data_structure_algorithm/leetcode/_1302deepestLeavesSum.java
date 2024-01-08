package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _1302deepestLeavesSum {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度；O(N)
         */
        public int deepestLeavesSum(TreeNode root) {
            int res = root.val;
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size(), sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res = sum;
            }
            return res;
        }


    }

}
