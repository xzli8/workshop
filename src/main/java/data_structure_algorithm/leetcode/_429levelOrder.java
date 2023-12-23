package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _429levelOrder {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> levelOrder(Node root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Queue<Node> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> tmp = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    Node cur = q.poll();
                    tmp.add(cur.val);
                    for (Node child : cur.children) {
                        if (child != null) q.offer(child);
                    }
                }
                res.add(tmp);
            }
            return res;
        }

    }

}
