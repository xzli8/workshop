package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _3249countGoodNodes {

    public static class Solution1 {

        /**
         DFS
         TC: O(N)
         SC: O(N)
         */
        public int countGoodNodes(int[][] edges) {
            // build adjacent list
            int n = edges.length + 1;
            g = new List[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }
            dfs(0, -1);
            return res;
        }

        private int res = 0;
        private List<Integer>[] g;

        private int dfs(int node, int parent) {
            boolean valid = true;
            int size = 0, preChildSize = -1;
            for (int child : g[node]) {
                if (child == parent) continue;
                int childSize = dfs(child, node);
                if (preChildSize == -1) {
                    preChildSize = childSize;
                } else if (childSize != preChildSize) {
                    valid = false;
                }
                size += childSize;
            }
            if (valid) res++;
            return size + 1;
        }

    }

}
