package data_structure_algorithm.leetcode;

public class _261validTree {

    /**
     * 题目链接：https://www.lintcode.com/problem/178/
     */

    public static class Solution1 {

        /**
         并查集
         */
        public boolean validTree(int n, int[][] edges) {
            // 初始化n个节点的并查集
            UnionFind uf = new UnionFind(n);

            // 遍历所有边，将所有边的两个节点进行连接
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1];
                // 若边的两个节点已经在同一个连通分量中，会产生环
                if (uf.connected(u, v)) {
                    return false;
                }
                // 若边的两个节点不在同一个连通分量中，可以是树的一部分
                uf.union(u, v);
            }
            // 最后判断是否是一棵树(对应一个连通分量)
            return uf.count() == 1;
        }

        // 并查集
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

            public boolean connected(int p, int q) {
                return find(p) == find(q);
            }

            public int count() {
                return count;
            }
        }

    }

}
