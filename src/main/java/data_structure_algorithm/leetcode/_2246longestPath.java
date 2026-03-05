package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2246longestPath {

    public static class Solution1 {

        /**
         DFS + 贪心: O(N), O(N)
         Note: 先构建树(图)，然后DFS求解
         Ref: https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/solutions/1427611/by-endlesscheng-92fw/
         */
        public int longestPath(int[] parent, String s) {
            // 根据parent构建图(树)
            int n = parent.length;
            g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int i = 1; i < n; i++) {
                g[parent[i]].add(i);
            }

            // dfs
            this.s = s.toCharArray();
            dfs(0);
            return res + 1;
        }

        private List<Integer>[] g;
        private char[] s;
        private int res;

        private int dfs(int i) {
            int maxLen = 0;
            for (int j : g[i]) {
                int len = dfs(j) + 1;
                if (s[j] != s[i]) {
                    // 贪心：最大值 + 次大值(以i为根节点的路径，类似题：543, 1245)
                    res = Math.max(res, maxLen + len);
                    maxLen = Math.max(maxLen, len);
                }
            }
            return maxLen;
        }

    }


}
