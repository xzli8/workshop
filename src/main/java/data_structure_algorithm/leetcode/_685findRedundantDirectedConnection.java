package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _685findRedundantDirectedConnection {

    public static class Solution1 {

        /**
         * UnionFind: O(NlogN), O(N)
         * Note: 首先计算每个节点的入度，如果存在入度为2的节点，我们定位到该节点对应的两条边，分别记为dup[0]和dup[1]。如果在删除dup[1]之后，
         *      剩余的边无法形成树，则说明dup[0]是需要删除的边；否则dup[1]是需要删除的边。如果不存在入度为2的节点，我们遍历数组edges，对于每条边(u, v)，
         *      我们使用并查集维护节点之间的连通性。如果u和v已经连通，说明图中存在有向环，此时当前边即为需要删除的边。
         */
        public int[] findRedundantDirectedConnection(int[][] edges) {
            // 计算入度
            int n = edges.length;
            int[] ind = new int[n];
            for (int[] e : edges) {
                ++ind[e[1] - 1];
            }
            // 找出入度为2的节点
            List<Integer> dup = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (ind[edges[i][1] - 1] == 2) {
                    dup.add(i);
                }
            }

            // 用并查集检查dup[0]还是dup[1]是要被删除的边
            UnionFind uf = new UnionFind(n);
            if (!dup.isEmpty()) {
                for (int i = 0; i < n; ++i) {
                    if (i == dup.get(1)) {
                        continue;
                    }
                    if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                        return edges[dup.get(0)];
                    }
                }
                return edges[dup.get(1)];
            }

            // 当不存在入度为2的节点时，用并查集维护连通性，检查是否有环
            for (int i = 0;; ++i) {
                // 如果u和v已经连通，说明图中存在有向环，此时当前边即为需要删除的边
                if (!uf.union(edges[i][0] - 1, edges[i][1] - 1)) {
                    return edges[i];
                }
            }
        }

        class UnionFind {
            private int count;
            private int[] parents;

            public UnionFind(int n) {
                this.count = n;
                parents = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
            }

            // 返回某节点x的根节点(核心方法)
            public int find(int x) {
                if (parents[x] != x) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            // 如果p和q已经连通，返回false
            public boolean union(int p, int q) {
                int rootP = find(p), rootQ = find(q);
                if (rootP == rootQ) return false;
                parents[rootP] = rootQ;
                count--;
                return true;
            }
        }


    }

}
