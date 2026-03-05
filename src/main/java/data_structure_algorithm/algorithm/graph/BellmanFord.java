package data_structure_algorithm.algorithm.graph;

import java.util.Arrays;

/**
 * Bellman-Ford 最短路 —— 允许负权边，单源
 *
 * 核心：不挑顺序，直接对 "所有边" 做一轮松弛，重复 V-1 轮。
 *      第 k 轮结束后，所有 "最多经过 k 条边" 的最短路都已经正确。
 *
 * 为什么是 V-1 轮：任何不含环的最短路最多有 V-1 条边。
 *                 如果第 V 轮还能松弛成功，说明图里有负权环，最短路不存在。
 *
 * 时间：O(V * E)    空间：O(V)
 * 比 Dijkstra 慢很多，只在两种情况下用：有负权边；或者题目限制 "最多走 K 条边"。
 *
 * 典型题：787 K 站中转内最便宜的航班（限制轮数的招牌题）
 * SPFA 是它的队列优化版：只把 "被松弛过的点" 重新入队，平均更快，最坏仍是 O(V * E)。
 */
public class BellmanFord {

    private static final int INF = Integer.MAX_VALUE / 2;

    /**
     * edges 每个元素是 {u, v, w}，表示 u -> v 权重 w（可为负）
     * 返回 src 到每个点的最短距离，不可达为 INF；如果存在从 src 可达的负权环，返回 null
     */
    public static int[] shortestPath(int n, int[][] edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int round = 0; round < n - 1; round++) {
            boolean updated = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] == INF) continue;       // 起点都还没到，别拿 INF 去松弛
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                }
            }
            if (!updated) break;                    // 提前收敛，常见优化
        }

        // 第 V 轮检测负环：还能松弛就说明有负环
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != INF && dist[u] + w < dist[v]) return null;
        }
        return dist;
    }

    /**
     * 限制边数版本（787 题）：从 src 到 dst，最多经过 k 条边的最短距离，不可达返回 -1
     *
     * 注意：每一轮必须基于 "上一轮的快照" 做松弛，否则同一轮内会连锁传播，
     *      一轮走出多条边，"最多 k 条边" 的限制就失效了。
     */
    public static int shortestPathWithinKEdges(int n, int[][] edges, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        for (int round = 0; round < k; round++) {
            int[] prev = dist.clone();              // 快照
            boolean updated = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (prev[u] == INF) continue;
                if (prev[u] + w < dist[v]) {
                    dist[v] = prev[u] + w;
                    updated = true;
                }
            }
            if (!updated) break;
        }
        return dist[dst] == INF ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        // 0 -> 1 (4), 0 -> 2 (5), 1 -> 2 (-3), 2 -> 3 (2)
        int n = 4;
        int[][] edges = {{0, 1, 4}, {0, 2, 5}, {1, 2, -3}, {2, 3, 2}};
        // 期望 [0, 4, 1, 3]：0->1->2 = 4 + (-3) = 1，比直达的 5 更短
        System.out.println("Bellman-Ford dist: " + Arrays.toString(shortestPath(n, edges, 0)));

        // 加一条 3 -> 1 (-4) 形成负环 1 -> 2 -> 3 -> 1，总权 -3 + 2 - 4 = -5
        int[][] withNegCycle = {{0, 1, 4}, {0, 2, 5}, {1, 2, -3}, {2, 3, 2}, {3, 1, -4}};
        System.out.println("负环检测(期望 null): " + Arrays.toString(shortestPath(n, withNegCycle, 0)));

        // 787 题样例：n=4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src=0, dst=3, k=1
        // 最多 1 次中转 = 最多 2 条边，期望 700（0->1->3）
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        System.out.println("最多 1 站中转(期望 700): " + shortestPathWithinKEdges(4, flights, 0, 3, 1 + 1));
    }

}
