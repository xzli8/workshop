package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lintcode_3686diameterOfBinaryTree {

    /**
     * ref:https://www.lintcode.com/problem/3686/description
     */

    public static class Solution1 {

        // Definition for Undirected graph.
        class UndirectedGraphNode {
            int label;
            List<UndirectedGraphNode> neighbors;
            UndirectedGraphNode(int x) {
                label = x;
                neighbors = new ArrayList<UndirectedGraphNode>();
            }
        }

         /**
             DFS
         */
         public int diameter(UndirectedGraphNode root) {
             dfs(root);
             return maxLen;
         }

         private int maxLen = 0;
         private int dfs(UndirectedGraphNode cur) {
             if (cur == null) return 0;
             List<Integer> lens = new ArrayList<>();
             for (UndirectedGraphNode neighbor : cur.neighbors) {
                 lens.add(dfs(neighbor));
             }
             Collections.sort(lens, (len1, len2) -> len2 - len1);
             int firstMaxLen = lens.size() > 0 ? lens.get(0) : 0;
             int secondMaxLen = lens.size() > 1 ? lens.get(1) : 0;
             maxLen = Math.max(maxLen, firstMaxLen + secondMaxLen);
             return firstMaxLen + 1;
         }

    }



    public static class Solution2 {

        // Definition for Undirected graph.
        class UndirectedGraphNode {
            int label;
            List<UndirectedGraphNode> neighbors;
            UndirectedGraphNode(int x) {
                label = x;
                neighbors = new ArrayList<UndirectedGraphNode>();
            }
        }

        /**
         DFS
         */
        public int diameter(UndirectedGraphNode root) {
            dfs(root);
            return maxLen;
        }

        private int maxLen = 0;
        private int dfs(UndirectedGraphNode cur) {
            if (cur == null) return 0;
            int firstMaxLen = 0, secondMaxLen = 0;
            for (UndirectedGraphNode neighbor : cur.neighbors) {
                int neighborMaxLen = dfs(neighbor);
                if (neighborMaxLen >= firstMaxLen) {
                    secondMaxLen = firstMaxLen;
                    firstMaxLen = neighborMaxLen;
                } else if (neighborMaxLen >= secondMaxLen) {
                    secondMaxLen = neighborMaxLen;
                }
            }
            maxLen = Math.max(maxLen, firstMaxLen + secondMaxLen);
            return firstMaxLen + 1;
        }

    }


}
