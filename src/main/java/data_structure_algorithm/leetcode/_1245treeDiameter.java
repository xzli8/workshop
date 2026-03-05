package data_structure_algorithm.leetcode;

import java.util.*;

public class _1245treeDiameter {

    /**
     * Ref: https://leetcode.doocs.org/lc/1245/
     *      lintcode3663:https://www.lintcode.com/problem/3663/description
     */

    public static class Solution1 {

        /**
         DFS: O(NlogN), O(N)
         */
         public int treeDiameter(int[][] edges) {
             // 将矩阵转换成多叉树结构，用map记录节点及其子节点
             for (int[] edge : edges) {
                 node2Children.putIfAbsent(edge[0], new ArrayList<>());
                 node2Children.putIfAbsent(edge[1], new ArrayList<>());
                 node2Children.get(edge[0]).add(edge[1]);
                 node2Children.get(edge[1]).add(edge[0]);
             }

             // dfs求直径
             dfs(edges[0][0], -1);
             return maxLen;
         }

         private Map<Integer, List<Integer>> node2Children = new HashMap<>();
         private int maxLen = 0;
         private int dfs(int cur, int prev) {
             List<Integer> children = node2Children.get(cur);
             if (children == null) return 1;
             List<Integer> lens = new ArrayList<>(children.size());
             for (int child : children) {
                 if (child == prev) continue;    // 防止走回头路
                 lens.add(dfs(child, cur));
             }
             // 因为只需要求最大与第二大，这里可以不用排序，用两个变量优化(见Solution2)
             lens.sort((len1, len2) -> len2 - len1);
             int firstMaxLen = lens.size() > 0 ? lens.get(0) : 0;
             int secondMaxLen = lens.size() > 1 ? lens.get(1) : 0;
             maxLen = Math.max(maxLen, firstMaxLen + secondMaxLen);
             return firstMaxLen + 1;
         }

    }



    public static class Solution2 {

        /**
         DFS: O(N), O(N)
         */
        public int treeDiameter(int[][] edges) {
            // 将矩阵转换成多叉树结构，用map记录节点及其子节点
            for (int[] edge : edges) {
                node2Children.putIfAbsent(edge[0], new ArrayList<>());
                node2Children.putIfAbsent(edge[1], new ArrayList<>());
                node2Children.get(edge[0]).add(edge[1]);
                node2Children.get(edge[1]).add(edge[0]);
            }

            // dfs求直径
            dfs(edges[0][0], -1);
            return maxLen;
        }

        private Map<Integer, List<Integer>> node2Children = new HashMap<>();
        private int maxLen = 0;
        private int dfs(int cur, int prev) {
            List<Integer> children = node2Children.get(cur);
            if (children == null) return 1;
            int firstMaxLen = 0, secondMaxLen = 0;
            for (int child : children) {
                if (child == prev) continue;    // 防止走回头路
                int childMaxLen = dfs(child, cur);
                if (childMaxLen >= firstMaxLen) {
                    secondMaxLen = firstMaxLen;
                    firstMaxLen = childMaxLen;
                } else if (childMaxLen >= secondMaxLen) {
                    secondMaxLen = childMaxLen;
                }
            }
            maxLen = Math.max(maxLen, firstMaxLen + secondMaxLen);
            return firstMaxLen + 1;
        }

    }

}
