package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _743networkDelayTime {

    public static class Solution1 {

        /**
         Dijkstra算法：有向正权图
         */
        public int networkDelayTime(int[][] times, int n, int k) {

            // 构造图(邻接表的形式记录图)
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] time : times) {
                // 因为节点编号从1开始，所以需要-1转换为从0开始
                int from = time[0] - 1, to = time[1] - 1, weight = time[2];
                graph[from].add(new int[] {to, weight});
            }

            // 启动dijstra算法
            int[] dist = dijkstra(k - 1, graph);

            // 找到最长路径
            int res = 0;
            for (int i = 0; i < n; i++) {
                // 有节点不可达，返回-1
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                res = Math.max(res, dist[i]);
            }
            return res;
        }

        public class State {
            int id;
            int dist;

            public State(int id, int dist) {
                this.id = id;
                this.dist = dist;
            }
        }

        // 计算从起点到其余各点的最短路径
        public int[] dijkstra(int start, List<int[]>[] graph) {
            // 记录最短路径的权重
            int n = graph.length;
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            // 优先队列，dist较小的排在前面
            PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> s1.dist - s2.dist);
            pq.offer(new State(start, 0));

            // 从起点开始BFS
            while (!pq.isEmpty()) {
                // 处理当前节点
                State cur = pq.poll();

                // 已经有一条更短的路径能到达当前节点，当前节点不加入路径
                // 加入优先队列不意味着加入路径，只有从当前节点出发(即pq.poll()，从优先队列中取出最小节点)，当前节点才算加入了路径
                // 加入优先队列，只是一种尝试，从当前节点出发，才说明当前节点加入了队列
                if (cur.dist > dist[cur.id]) {
                    continue;
                }

                // 访问当前节点的相邻节点
                for (int[] adj : graph[cur.id]) {
                    int adjId = adj[0];
                    int adjDist = adj[1] + cur.dist;

                    // 如果从当前节点出发的路径比之前的路径更短，则更新距离并加入优先队列
                    if (adjDist < dist[adjId]) {
                        dist[adjId] = adjDist;
                        pq.offer(new State(adjId, adjDist));
                    }
                }
            }

            // 返回从起点到其余各点的最短距离
            return dist;
        }

    }


    /**
     * ShortestPath: 给两个数组，分别是图中所有相连的edge和每个edge的长度，另外给了起点和终点，需要求出从起点到终点最短路径。
     */
    public static class Solution2 {

        public int shortestPath(int[][] edges, int[] lengths, int start, int end) {
            // 若没给 n，从端点推出节点数（假设编号从 0 开始）
            int n = 0;
            for (int[] e : edges) n = Math.max(n, Math.max(e[0], e[1]) + 1);

            // 建邻接表 —— 无向图：两个方向都加；若是有向图，删掉第二行
            List<int[]>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0], v = edges[i][1], w = lengths[i];
                graph[u].add(new int[]{v, w});
                graph[v].add(new int[]{u, w});   // 有向图就删掉这行
            }

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            // int[0] -> 节点编号; int[1] -> 节点距离起点的dist
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            pq.offer(new int[]{start, 0});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int id = cur[0], d = cur[1];
                if (id == end) return d;        // 终点第一次出队即最短，提前返回
                if (d > dist[id]) continue;     // 过期条目，跳过
                for (int[] adj : graph[id]) {
                    int nb = adj[0], nd = d + adj[1];
                    if (nd < dist[nb]) {
                        dist[nb] = nd;
                        pq.offer(new int[]{nb, nd});
                    }
                }
            }
            return -1;   // 终点不可达

        }
    }

}
