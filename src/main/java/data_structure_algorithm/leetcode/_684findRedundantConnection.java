package data_structure_algorithm.leetcode;

public class _684findRedundantConnection {

    public static class Solution1 {

        /**
         UnionFind
         */
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length + 1;
            UnionFind uf = new UnionFind(n);
            int[] res = new int[2];
            for (int[] edge : edges) {
                if (uf.isConnected(edge[0], edge[1])) {
                    res = edge;
                } else {
                    uf.union(edge[0], edge[1]);
                }
            }
            return res;
        }

        class UnionFind {
            int count;
            int[] parents;

            public UnionFind(int n) {
                count = n;
                parents = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
            }

            public int findRoot(int x) {
                if (parents[x] != x) {
                    parents[x] = findRoot(parents[x]);
                }
                return parents[x];
            }

            public void union(int p, int q) {
                int rootP = findRoot(p), rootQ = findRoot(q);
                if (rootP == rootQ) return;
                parents[rootP] = rootQ;
                count--;
            }

            public boolean isConnected(int p, int q) {
                return findRoot(p) == findRoot(q);
            }

        }

    }

}
