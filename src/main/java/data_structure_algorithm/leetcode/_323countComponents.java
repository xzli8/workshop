package data_structure_algorithm.leetcode;

import java.util.*;

public class _323countComponents {

    /**
     * Ref: https://leetcode.doocs.org/lc/323/
     * Lintcode：https://www.lintcode.com/problem/3651/
     */

    public static class Solution1 {

        /**
         并查集(Union-Find)
         */
        public int countComponents(int n, int[][] edges) {
            UnionFind uf = new UnionFind(n);
            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }
            return uf.count();
        }

        // 并查集(Union-Find)
        class UnionFind {

            // 连通分量的个数
            private int count;

            // 每个节点的父节点
            private int[] parent;

            // 构造函数
            public UnionFind (int n) {
                this.count = n;
                parent = new int[n];
                // 对于每个节点，父节点初始化为自身
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // 连通两个点p, q
            public void union(int p, int q) {
                // 找p, q的根节点
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }

                // 将p, q合并到一棵树上，连通数量减1
                parent[rootP] = rootQ;
                count--;
            }

            // 返回某个节点的根节点
            private int find(int x) {
                // 普通写法，可能导致树不平衡，时间复杂度退化为O(N)
                // 根节点的 parent[x] == x
                // while (parent[x] != x) {
                //     x = parent[x];
                // }
                // return x;

                // 路径压缩，会保持树平衡，时间复杂度简化为O(1)
//                if (parent[x] != x) {
//                    parent[x] = find(parent[x]);
//                }
//                return parent[x];

                // 路径压缩的另一种写法
                while (parent[x] != x) {
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            public int count() {
                return count;
            }
        }

    }


    public static class Solution2 {


        /**
         * DFS/BFS: O(N), O(N)
         * Note: 我们先根据给定的边构建一个邻接表g，其中g[i]表示节点i的所有邻居节点。然后我们遍历所有节点，
         *      对于每个节点，我们使用DFS/BFS遍历所有与其相邻的节点，并将其标记为已访问，直到所有与其相邻的节点都被访问过，
         *      这样我们就找到了一个连通分量，答案加一。然后我们继续遍历下一个未访问的节点，直到所有节点都被访问过。
         */
        public int countComponentsDFS(int n, int[][] edges) {
            g = new List[n];
            vis = new boolean[n];
            Arrays.setAll(g, k -> new ArrayList<>());
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                g[a].add(b);
                g[b].add(a);
            }

            int res = 0;
            for (int i = 0; i < n; ++i) {
                res += dfs(i);
            }
            return res;
        }

        private List<Integer>[] g;
        private boolean[] vis;
        private int dfs(int i) {
            if (vis[i]) {
                return 0;
            }
            vis[i] = true;
            for (int j : g[i]) {
                dfs(j);
            }
            return 1;
        }


        /**
         * BFS
         */
        public int countComponentsBFS(int n, int[][] edges) {
            List<Integer>[] g = new List[n];
            Arrays.setAll(g, k -> new ArrayList<>());
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                g[a].add(b);
                g[b].add(a);
            }

            int res = 0;
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; ++i) {
                if (vis[i]) continue;
                vis[i] = true;
                ++res;

                Deque<Integer> q = new ArrayDeque<>();
                q.offer(i);
                while (!q.isEmpty()) {
                    int a = q.poll();
                    for (int b : g[a]) {
                        if (!vis[b]) {
                            vis[b] = true;
                            q.offer(b);
                        }
                    }
                }
            }
            return res;
        }

    }

}
