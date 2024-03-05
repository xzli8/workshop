package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _1976countPaths {

    public static class Solution1 {

        /**
         Dijkstra算法:有向正权图
         */
        public int countPaths(int n, int[][] roads) {
            // 构建图(邻接表):无向图转换成双向图
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<int[]>();
            for (int[] road : roads) {
                int from = road[0], to = road[1], weight = road[2];
                graph[from].add(new int[] {to, weight});
                graph[to].add(new int[] {from, weight});
            }

            // 记录从起点出发到每个点最短距离的数量
            int mod = 1000000007;
            int[] ways = new int[n];
            ways[0] = 1;

            // 记录从起点出发到每个点的加权距离
            long[] dists = new long[n];
            Arrays.fill(dists, Long.MAX_VALUE);
            dists[0] = 0;

            // BFS
            PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> Long.compare(s1.dist, s2.dist));
            pq.offer(new State(0, 0));
            while (!pq.isEmpty()) {
                State cur = pq.poll();
                if (cur.dist > dists[cur.id]) continue;
                for (int[] adj : graph[cur.id]) {
                    int adjId = adj[0];
                    long adjDist = adj[1] + cur.dist;
                    if (adjDist < dists[adjId]) {
                        ways[adjId] = ways[cur.id];
                        dists[adjId] = adjDist;
                        pq.offer(new State(adjId, adjDist));
                    } else if (adjDist == dists[adjId]) {
                        ways[adjId] = (ways[adjId] + ways[cur.id]) % mod;
                    }
                }
            }
            return ways[n - 1];
        }

        public class State {
            int id;
            long dist;
            public State(int id, long dist) {
                this.id = id;
                this.dist = dist;
            }
        }

    }

}
