package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1584minCostConnectPoints {

    public static class Solution1 {

        /**
         最小生成树(Kruskal or Prim)
         */
        public int minCostConnectPoints(int[][] points) {
            // 计算所有点的曼哈顿距离，生成所有的边及其权重(曼哈顿距离)
            int n = points.length;
            List<int[]> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int xi = points[i][0], yi = points[i][1];
                    int xj = points[j][0], yj = points[j][1];
                    edges.add(new int[] {i, j, Math.abs(xi - xj) + Math.abs(yi - yj)});
                }
            }

            // 将所有边按照权重大小，从小到大排序
            Collections.sort(edges, (e1, e2) -> e1[2] - e2[2]);

            // 遍历所有边，优先取权重小的边用来构建树
            UnionFind uf = new UnionFind(n);
            int sum = 0;
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];

                // 若这条边可能产生环，不连接(环意味着之前已经连通了，再用一条边连通徒增成本)
                if (uf.connected(u, v)) {
                    continue;
                }

                // 若这条边不可能产生环，连接，并且计算成本
                uf.union(u, v);
                sum += w;
            }

            // 这里任意两点均能被连起来，不可能存在未连通的情况
            return sum;
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
