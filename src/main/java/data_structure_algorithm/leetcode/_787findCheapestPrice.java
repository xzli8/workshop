package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _787findCheapestPrice {

    public static class Solution1 {

        /**
         * Bellman-Ford: 时间 O(k·E)，空间 O(n)
         * Note: 做 i 轮松弛后，dist[] 就是「最多用 i 条边」能到达的最短距离。所以做 k+1 轮即可
         */
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[src] = 0;

            // 最多 k 站 => 最多 k+1 条边 => 松弛 k+1 轮
            for (int i = 0; i <= k; i++) {
                int[] prev = dist.clone();      // 关键：基于上一轮快照松弛(松弛时必须读上一轮的快照 prev，更新写进 dist)
                for (int[] f : flights) {
                    int u = f[0], v = f[1], w = f[2];
                    if (prev[u] != Integer.MAX_VALUE && prev[u] + w < dist[v]) {
                        dist[v] = prev[u] + w;
                    }
                }
            }
            return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
        }

    }

}
