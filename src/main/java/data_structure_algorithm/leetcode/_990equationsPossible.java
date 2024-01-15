package data_structure_algorithm.leetcode;

public class _990equationsPossible {

    public static class Solution1 {

        /**
         并查集：将equations中的算式根据"=="和"!="分为两个部分，先根据"=="构建并查集，然后根据"!="检查是否破坏了连通关系
         */
        public boolean equationsPossible(String[] equations) {

            // 构建并查集(一共26个英文字母)
            UnionFind uf = new UnionFind(26);

            // 让相等的字母形成连通分量
            for (String eq : equations) {
                if (eq.charAt(1) == '=') {
                    uf.union(eq.charAt(0) - 'a', eq.charAt(3) - 'a');
                }
            }

            // 检查不等关系是否打破相等关系的连通性
            for (String eq : equations) {
                if (eq.charAt(1) == '!') {
                    if (uf.connected(eq.charAt(0) - 'a', eq.charAt(3) - 'a')) {
                        return false;
                    }
                }
            }
            return true;
        }

        // 并查集
        class UnionFind {

            private int count;

            private int[] parent;

            public UnionFind (int n) {
                this.count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
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
        }

    }

}
