package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.Node;

import java.util.ArrayDeque;
import java.util.Queue;

public class _559maxDepth {

    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int maxDepth(Node root) {
             if (root == null) return 0;
             int maxDepth = 0;
             for (Node child : root.children) {
                 maxDepth = Math.max(maxDepth(child), maxDepth);
             }
             return maxDepth + 1;
         }

    }



    public static class Solution2 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxDepth(Node root) {
            if (root == null) return 0;

            int depth = 0;
            Queue<Node> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                depth++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Node node = q.poll();
                    for (Node child : node.children) {
                        if (child != null) q.offer(child);
                    }
                }
            }
            return depth;
        }

    }


}
