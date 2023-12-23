package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _515largestValues {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size(), max = Integer.MIN_VALUE;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                    max = Math.max(max, cur.val);
                }
                res.add(max);
            }
            return res;
        }

    }

}
