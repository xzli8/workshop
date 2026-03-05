package data_structure_algorithm.leetcode;

import java.util.*;

public class _261validTree {

    /**
     * Ref: https://leetcode.doocs.org/lc/261/
     * Lintcode：https://www.lintcode.com/problem/178/
     *
     * UnionFind Reference: https://segmentfault.com/a/1190000038593250
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
                // 一开始互不连通，父节点指针初始指向自己
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

                // 如果某两个节点被连通，则让其中的（任意）一个节点的根节点接到另一个节点的根节点上
                parent[rootP] = rootQ;
                count--;
            }

            private int find(int x) {
                // 根节点的 parent[x] == x
                // 初始做法：可能导致不平衡，增加时间复杂度
//                while (parent[x] != x)
//                    x = parent[x];

                // 路径压缩-写法1
//                while (parent[x] != x) {
//                    parent[x] = parent[parent[x]];
//                    x = parent[x];
//                }

                // 路径压缩-写法2
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


    public static class Solution2 {

        // DFS: cycle detection
        public Boolean graph_valid_tree(Integer n, int[][] edges) {
            g = new List[n];
            visited = new HashSet<>();

            Arrays.setAll(g, k -> new ArrayList<>());
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }
            if (!dfs(0, -1)) return false;
            return visited.size() == n;
        }

        private List<Integer>[] g;
        private Set<Integer> visited;
        private boolean dfs(int node, int parent) {
            visited.add(node);
            for (int neighbor : g[node]) {
                if (visited.contains(neighbor)) {
                    if (neighbor != parent) return false;
                } else if (!dfs(neighbor, node)) {
                    return false;
                }
            }
            return true;
        }

    }


    public static class Solution3 {

        /**
         * DO NOT WORK: no cycle detection
         * DFS/BFS:
         * Note: 使用DFS/BFS来判断是否存在环，用一个数组visited来记录访问过的节点，搜索时，我们先将节点标记为已访问，然后遍历与该节点相邻的节点，
         * 如果相邻节点已经访问过，则跳过，否则递归访问相邻节点。最后，我们判断是否所有节点都被访问过，如果有未访问过的节点，说明无法构成树，返回false
         */
        public boolean validTree(int n, int[][] edges) {
            if (edges.length != n - 1) {
                return false;
            }
            g = new List[n];
            Arrays.setAll(g, k -> new ArrayList<>());
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                g[a].add(b);
                g[b].add(a);
            }
            dfs(0);
            return visited.size() == n;
        }

        private List<Integer>[] g;
        private Set<Integer> visited = new HashSet<>();
        private void dfs(int i) {
            visited.add(i);
            for (int j : g[i]) {
                if (!visited.contains(j)) {
                    dfs(j);
                }
            }
        }

    }



}
