package data_structure_algorithm.leetcode;

public class _547findCircleNum {

    public static class Solution1 {

        /**
         并查集
         */
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1) {
                        uf.union(i, j);
                    }
                }
            }
            return uf.count();
        }

        class UnionFind {

            private int count;
            private int[] parent;

            public UnionFind(int n) {
                count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int p, int q) {
                int rootP = find(p), rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }

                parent[rootP] = rootQ;
                count--;
            }

            private int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            public int count() {
                return count;
            }
        }

    }

}
