package data_structure_algorithm.algorithm.graph;

import java.util.*;

/**
 * Dijkstra 最短路 —— 边权非负，单源
 *
 * 核心：贪心。维护每个点当前已知的最短距离，每次从最小堆里取出 "距离最小且尚未确定" 的点，
 *      用它去松弛所有邻居。一个点出堆的那一刻，它的距离就是最终答案。
 *
 * 为什么正确：边权非负 => 多走一条边只会更远，所以堆顶最小的那个点不可能再被别的路径改善。
 *            这也是它不能处理负权边的根本原因。
 *
 * 时间：O((V + E) log V)（二叉堆）  空间：O(V + E)
 * 稠密图（E 接近 V²）可以用 O(V²) 的朴素版本，每轮线性扫最小值，不用堆。
 *
 * 典型题：743 网络延迟时间、1631 最小体力消耗路径（距离定义改成 "路径上最大边"）、
 *        778 水位上升的泳池、1514 概率最大的路径（加法改乘法、min 改 max）、
 *        787 K 站中转（状态里再带一维步数）
 */
public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE / 2;   // 除 2 防止 dist[u] + w 溢出

    /**
     * adj.get(u) 里每个元素是 {v, w}，表示 u -> v 权重 w
     * 返回 src 到每个点的最短距离，不可达为 INF
     */
    public static int[] shortestPath(int n, List<List<int[]>> adj, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // 堆里存 {node, dist}，按 dist 升序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];

            // 关键：跳过过期记录。同一个点可能被多次入堆，只有第一次出堆（最小的那次）有效。
            // 漏掉这一行答案仍然正确，但复杂度会退化。
            if (d > dist[u]) continue;

            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    /**
     * 带路径还原：prev[v] 记录 v 是从哪个点松弛过来的
     * 返回 src -> target 的节点序列，不可达返回空列表
     */
    public static List<Integer> shortestPathWithRoute(int n, List<List<int[]>> adj, int src, int target) {
        int[] dist = new int[n];
        int[] prev = new int[n];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;
            if (u == target) break;             // 单目标可以提前退出

            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    prev[v] = u;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        LinkedList<Integer> route = new LinkedList<>();
        if (dist[target] == INF) return route;
        for (int v = target; v != -1; v = prev[v]) route.addFirst(v);
        return route;
    }

    public static void main(String[] args) {
        //      1
        //  0 -----> 1
        //  |        |
        //  4        2
        //  |        v
        //  v   1    2
        //  3 -----> 2
        // 以及 1 -> 3 权 5
        int n = 4;
        int[][] edges = {{0, 1, 1}, {0, 3, 4}, {1, 2, 2}, {3, 2, 1}, {1, 3, 5}};
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) adj.get(e[0]).add(new int[]{e[1], e[2]});

        // 期望 [0, 1, 3, 4]
        System.out.println("Dijkstra dist: " + Arrays.toString(shortestPath(n, adj, 0)));
        // 期望 [0, 1, 2]
        System.out.println("Dijkstra route 0->2: " + shortestPathWithRoute(n, adj, 0, 2));
    }

}
