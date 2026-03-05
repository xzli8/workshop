package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _305numIslands2 {

    /**
     * Ref: https://leetcode.doocs.org/lc/305/
     * Lintcode:https://www.lintcode.com/problem/434
     */

    public static class Solution1 {

        /**
         * UnionFind: O(KlogMN, O(MN)
         * Note:
         */
        public List<Integer> numIslands2(int m, int n, int[][] positions) {
            int[][] grid = new int[m][n];

            int cnt = 0;
            List<Integer> res = new ArrayList<>();
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            UnionFind uf = new UnionFind(m * n);
            for (int[] p : positions) {
                int i = p[0], j = p[1];
                // 该位置已经是陆地，直接添加cnt到结果中
                if (grid[i][j] == 1) {
                    res.add(cnt);
                    continue;
                }

                // 先将该位置变成陆地，然后用并查集确定连通性:
                // 遍历该位置的上下左右四个方向，如果某个方向的位置为1，并且该位置与(i, j)不属于同一个连通分量，那么我们就将该位置与(i, j)进行合并，
                // 同时将cnt的值减少。遍历完该位置的上下左右四个方向之后，我们将cnt添加到答案中。
                grid[i][j] = 1;
                ++cnt;
                for (int k = 0; k < 4; k++) {
                    int x = i + dirs[k][0], y = j + dirs[k][1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1
                            && uf.union(i * n + j, x * n + y)) {
                        --cnt;
                    }
                }
                res.add(cnt);
            }
            return res;
        }

        class UnionFind {
            private int count;
            private int[] parents;

            public UnionFind(int n) {
                this.count = n;
                parents = new int[n];
                for (int i = 0; i < n; i++) {
                    parents[i] = i;
                }
            }

            // 返回某节点x的根节点(核心方法)
            public int find(int x) {
                if (parents[x] != x) {
                    parents[x] = find(parents[x]);
                }
                return parents[x];
            }

            public boolean union(int p, int q) {
                int rootP = find(p), rootQ = find(q);
                if (rootP == rootQ) return false;
                parents[rootP] = rootQ;
                count--;
                return true;
            }

            public int count() {
                return count;
            }
        }

    }

}
