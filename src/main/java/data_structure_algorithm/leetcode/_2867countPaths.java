package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2867countPaths {

    public static class Solution1 {

        /**
         埃氏筛 + DFS
         ref:https://leetcode.cn/problems/count-valid-paths-in-a-tree/solutions/2654126/tong-ji-shu-zhong-de-he-fa-lu-jing-shu-m-yyuw/
         https://leetcode.cn/problems/count-valid-paths-in-a-tree/solutions/2456716/tu-jie-on-xian-xing-zuo-fa-pythonjavacgo-tjz2/
         时间复杂度：
         空间复杂度：
         */
        public long countPaths(int n, int[][] edges) {
            // 埃氏筛(预处理)："204.计数质数"
            int max = 100001;
            isPrime = new boolean[max];
            Arrays.fill(isPrime, true);
            isPrime[1] = false;
            for (int i = 2; i * i < max; i++) {
                if (isPrime[i]) {
                    for (int j = i * i; j < max; j += i) isPrime[j] = false;
                }
            }

            // 将邻接矩阵转换成邻接表(初始化图)
            List<Integer>[] g = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }

            // DFS
            List<Integer> seen = new ArrayList<>();
            long res = 0;
            long[] count = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                if (!isPrime[i]) continue;  // 从以质数为根的节点开始
                long cur = 0;
                for (int j : g[i]) {
                    if (isPrime[j]) continue;   // 遇到质数就跳过
                    if (count[j] == 0) {
                        seen.clear();
                        dfs(g, seen, j, 0);
                        long cnt = seen.size();
                        for (int k : seen) count[k] = cnt;
                    }
                    res += count[j] * cur;
                    cur += count[j];
                }
                res += cur;
            }
            return res;
        }

        private boolean[] isPrime;
        private void dfs(List<Integer>[] g, List<Integer> seen, int i, int pre) {
            seen.add(i);
            for (int j : g[i]) {
                if (j != pre && !isPrime[j]) dfs(g, seen, j, i);
            }
        }

    }

}
