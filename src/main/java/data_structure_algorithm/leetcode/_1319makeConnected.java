package data_structure_algorithm.leetcode;

public class _1319makeConnected {

    public static class Solution1 {

        /**
         并查集：先连线，如果下一条线的两个节点之间已经是互联的，那么这条线可以先不用，后面用来连接两个不连通的分量
         */
        public int makeConnected(int n, int[][] connections) {
            UnionFind uf = new UnionFind(n);
            int unused = 0;
            for (int[] connection : connections) {
                int a = connection[0], b = connection[1];
                if (uf.isConnected(a, b)) {
                    unused++;
                    continue;
                }
                uf.connect(a, b);
            }
            return uf.count() - 1 <= unused ? uf.count() - 1 : -1;
        }

        /**
         并查集
         */
        public class UnionFind {
            // 连通分量的数目
            private int count;
            // 父节点数组
            private int[] parent;

            // 构造函数
            public UnionFind (int n) {
                count = n;
                parent = new int[n];
                // 每个节点的父节点初始化为自身，表示互不连通的n个分量
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // 返回连通分量的数目
            public int count() {
                return count;
            }

            // 寻找父节点
            private int findParent(int x) {
                // 路径压缩：所有节点都直连到根节点，树的高度降低为1
                if (parent[x] != x) {
                    parent[x] = findParent(parent[x]);
                }
                return parent[x];
            }

            // 判断两个节点是否连通
            public boolean isConnected(int p, int q) {
                return findParent(p) == findParent(q);
            }

            // 连通两个节点
            public void connect(int p, int q) {
                int rootP = findParent(p);
                int rootQ = findParent(q);
                if (rootP == rootQ) {
                    return;
                }
                parent[rootP] = rootQ;
                count--;
            }
        }

    }



    public static class Solution2 {

        /**
         并查集：如果连接线的数量大于n-1，则一定可以互联；否则一定不能互联
         */
        public int makeConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1;
            }

            UnionFind uf = new UnionFind(n);
            int unused = 0;
            for (int[] connection : connections) {
                int a = connection[0], b = connection[1];
                if (uf.isConnected(a, b)) {
                    unused++;
                    continue;
                }
                uf.connect(a, b);
            }
            return uf.count() - 1;
        }

        /**
         并查集
         */
        public class UnionFind {
            // 连通分量的数目
            private int count;
            // 父节点数组
            private int[] parent;

            // 构造函数
            public UnionFind (int n) {
                count = n;
                parent = new int[n];
                // 每个节点的父节点初始化为自身，表示互不连通的n个分量
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // 返回连通分量的数目
            public int count() {
                return count;
            }

            // 寻找父节点
            private int findParent(int x) {
                // 路径压缩：所有节点都直连到根节点，树的高度降低为1
                if (parent[x] != x) {
                    parent[x] = findParent(parent[x]);
                }
                return parent[x];
            }

            // 判断两个节点是否连通
            public boolean isConnected(int p, int q) {
                return findParent(p) == findParent(q);
            }

            // 连通两个节点
            public void connect(int p, int q) {
                int rootP = findParent(p);
                int rootQ = findParent(q);
                if (rootP == rootQ) {
                    return;
                }
                parent[rootP] = rootQ;
                count--;
            }
        }

    }

}
