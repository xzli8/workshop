package data_structure_algorithm.leetcode;

import java.util.*;

public class _444sequenceReconstruction {

    /**
     * Ref: https://leetcode.doocs.org/lc/444/
     */

    public static class Solution1 {

        /**
         * BFS拓扑排序: O(N), O(N)
         * Note: 遍历每个子序列seq，对于每个相邻的元素a和b，我们在a和b之间建立一条有向边a->b。同时统计每个节点的入度，进行BFS拓扑排序。
         */
        public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
            // 构建邻接表和入度
            int n = nums.length;
            int[] indeg = new int[n];
            List<Integer>[] g = new List[n];
            Arrays.setAll(g, k -> new ArrayList<>());
            for (List<Integer> seq : sequences) {
                for (int i = 1; i < seq.size(); ++i) {
                    int a = seq.get(i - 1) - 1, b = seq.get(i) - 1;
                    g[a].add(b);
                    ++indeg[b];
                }
            }

            // 入度为0的起点
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < n; ++i) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // BFS拓扑排序
            while (q.size() == 1) {
                int i = q.poll();
                for (int j : g[i]) {
                    if (--indeg[j] == 0) {
                        q.offer(j);
                    }
                }
            }
            return q.isEmpty();
        }

    }

}
