package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class _2583kthLargestLevelSum {

    public static class Solution1 {

        /**
         BFS + PriorityQueue
         时间复杂度：O(NlogK)
         空间复杂度：O(N + K)
         */
        public long kthLargestLevelSum(TreeNode root, int k) {
            if (root == null) return -1L;
            PriorityQueue<Long> pq = new PriorityQueue<>();
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                long sum = 0;
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    sum += cur.val;
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                pq.offer(sum);
                if (pq.size() > k) pq.poll();
            }
            return pq.size() == k ? pq.peek() : -1;
        }

    }

}
