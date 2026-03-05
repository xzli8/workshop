package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1135minimumCost {

    /**
     * Ref: https://leetcode.doocs.org/lc/1135/
     * Lintcode：https://www.lintcode.com/problem/3672/
     */

    public static class Solution1 {

        /**
         Kruskal(Prim)最小生成树: O(MlogM), O(N), M为边数，N为顶点数
         Note: Kruskal算法的基本思想(贪心)，每次从边集中选择一条最小的边，如果这条边连接的两个顶点不在同一个连通分量中，则将这条边加入到最小生成树中，否则舍弃这条边。
         */
        public int minimumCost(int n, int[][] connections) {
            // 初始化节点数量为n的并查集
            UnionFind uf = new UnionFind(n);

            // 将所有边按照权重大小，从小到大排序
            Arrays.sort(connections, (c1, c2) -> c1[2] - c2[2]);

            // 遍历所有边，优先选择权重小的边来构建树
            int sum = 0;
            for (int[] c : connections) {
                // 城市编号从1开始，需要-1转换为从0开始
                int u = c[0] - 1, v = c[1] - 1, w = c[2];

                // 这条边会产生环，不加入树(不加入树就能连通了，加入树徒增成本)
                if (uf.connected(u, v)) {
                    continue;
                }

                // 这条边不会产生环，加入树，并计算成本
                uf.union(u, v);
                sum += w;
            }

            // 连通量为1说明所有节点被连通
            return uf.count() == 1 ? sum : -1;
        }

        public class UnionFind {
            private int count;
            private int[] parent;

            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            private int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            public void union(int u, int v) {
                int rootU = find(u), rootV = find(v);
                if (rootU == rootV) {
                    return;
                }
                parent[rootU] = rootV;
                count--;
            }

            public boolean connected(int u, int v) {
                return find(u) == find(v);
            }

            public int count() {
                return count;
            }
        }

    }

}
