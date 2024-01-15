package data_structure_algorithm.leetcode;

public class _323countComponents {

    /**
     * 题目链接：https://www.lintcode.com/problem/3651/
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

            private int find(int x) {
                // 普通写法，可能导致树不平衡，时间复杂度退化为O(N)
                // while (parent[x] != x) {
                //     x = parent[x];
                // }
                // return x;

                // 路径压缩，会保持树平衡，时间复杂度简化为O(1)
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
