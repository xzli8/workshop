package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _3112minimumTime {

    public static class Solution1 {

        /**
         Dijkstra
         */
        public int[] minimumTime(int n, int[][] edges, int[] disappear) {
            // 构建图(邻接表)
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                int from = edge[0], to = edge[1], len = edge[2];
                graph[from].add(new int[] {to, len});
                graph[to].add(new int[] {from, len});
            }

            // 初始化时间数组
            int[] lens = new int[n];
            Arrays.fill(lens, Integer.MAX_VALUE);
            lens[0] = 0;

            // 优先队列：len较小的排在前面
            PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> s1.len - s2.len);
            pq.offer(new State(0, 0));
            while(!pq.isEmpty()) {
                // 处理当前节点
                State cur = pq.poll();

                // 如果当前节点的时间大于之前已记录过的最小时间，那么显然从这条路径过来的不是最短路径
                if (cur.len > lens[cur.id]) {
                    continue;
                }

                // 处理当前节点的相邻节点
                for (int[] adj : graph[cur.id]) {
                    int adjId = adj[0], adjLen = adj[1] + cur.len;

                    // 如果相邻节点已经消失了，显然不能到达
                    if (adjLen >= disappear[adjId]) continue;
                    if (adjLen < lens[adjId]) {
                        lens[adjId] = adjLen;
                        pq.offer(new State(adjId, adjLen));
                    }
                }
            }

            // 处理返回值
            for (int i = 0; i < n; i++) {
                if (lens[i] == Integer.MAX_VALUE) lens[i] = -1;
            }
            return lens;
        }


        // 表示节点状态
        class State {
            int id;
            int len;

            public State(int id, int len) {
                this.id = id;
                this.len = len;
            }
        }

    }

}
