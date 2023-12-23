package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _637averageOfLevels {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> averages = new ArrayList<Double>();
            if (root == null) return averages;

            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                double sum = 0.0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                averages.add(sum / size);
            }
            return averages;
        }

    }

}
