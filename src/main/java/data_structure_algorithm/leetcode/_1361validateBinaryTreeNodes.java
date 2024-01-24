package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1361validateBinaryTreeNodes {

    public static class Solution1 {

        /**
         并查集：树就是无环的连通图，判断连通图用并查集，同时需要判断是否有环，父子节点的关系等
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
            UnionFind uf = new UnionFind(n);
            for (int i = 0; i < n; i++) {
                // 左右子节点是同一个，不合法
                if (leftChild[i] != -1 && leftChild[i] == rightChild[i]) return false;
                // 合并左右子节点
                if (!uf.union(i, leftChild[i]) || !uf.union(i, rightChild[i])) return false;
            }
            return uf.unionCount() == 1;
        }

        // 并查集
        class UnionFind {
            private int n;
            private int[] parents, roots;

            public UnionFind(int n) {
                this.n = n;
                this.roots = new int[n];
                this.parents = new int[n];
                for (int i = 0; i < n; i++) roots[i] = i;
                Arrays.fill(parents, -1);
            }

            // 合并父子节点
            public boolean union(int parent, int child) {
                // 子节点为空，无需检查，直接返回true
                if (child == -1) return true;
                // 子节点已经有父节点了，返回false
                if (parents[child] != -1) return false;
                // 查找父子节点的根节点
                int rootParent = find(parent), rootChild = find(child);
                // 如果根节点相同，说明产生了环，返回false
                if (rootParent == rootChild) return false;
                // 合并父子节点
                roots[rootChild] = roots[rootParent];
                // 设置子节点的父节点
                parents[child] = parent;
                // 连同数量减一
                n--;
                return true;
            }

            private int find(int x) {
                if (roots[x] != x) roots[x] = find(roots[x]);
                return roots[x];
            }

            public int unionCount() {
                return n;
            }
        }

    }

}
