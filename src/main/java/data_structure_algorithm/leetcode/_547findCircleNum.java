package data_structure_algorithm.leetcode;

public class _547findCircleNum {

    public static class Solution1 {

        /**
         并查集: O(N), O(N)
         Note: 这里用邻接矩阵而不是邻接表给出的连通关系。
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


    public static class Solution2 {

        /**
         * DFS/BFS: O(N), O(N)
         * Note: 用数组visited标记是否被访问，然后用DFS/BFS遍历。
         */
        public int findCircleNum(int[][] isConnected) {
            g = isConnected;
            int n = g.length;
            visited = new boolean[n];
            int res = 0;
            for (int i = 0; i < n; ++i) {
                if (!visited[i]) {
                    dfs(i);
                    ++res;
                }
            }
            return res;
        }

        private int[][] g;
        private boolean[] visited;
        private void dfs(int i) {
            visited[i] = true;
            for (int j = 0; j < g.length; ++j) {
                if (!visited[j] && g[i][j] == 1) {
                    dfs(j);
                }
            }
        }

    }
}
