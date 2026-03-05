package data_structure_algorithm.algorithm.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * BFS 最短路 —— 所有边等权（或无权图）
 *
 * 核心：用队列逐层扩展。因为每条边代价相同，第一次到达某个节点时经过的层数就是最短距离，
 *      之后再到达它的任何路径都不可能更短。
 *
 * 适用：无权图 / 网格题（上下左右每步代价 1）。有权图不能用，哪怕权重只是 1 和 2。
 * 时间：O(V + E)    空间：O(V)
 *
 * 典型题：1091 二进制矩阵中的最短路径、994 腐烂的橘子、542 01矩阵、
 *        1197 骑士最短路径、815 公交路线、127 单词接龙、743 之类有权的就别用它
 */
public class BFS {

    /**
     * 邻接表版本：返回 src 到每个点的最短距离，不可达为 -1
     */
    public static int[] shortestPath(int n, List<List<Integer>> adj, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);          // -1 同时充当 "未访问" 标记
        dist[src] = 0;

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(src);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj.get(u)) {
                if (dist[v] != -1) continue;    // 已经访问过，第一次到达时就是最短的
                dist[v] = dist[u] + 1;
                queue.offer(v);
            }
        }
        return dist;
    }

    /**
     * 网格版本：从 (sr, sc) 到 (tr, tc) 的最少步数，0 表示可走，1 表示障碍，不可达返回 -1
     * 技巧：入队时就标记 visited（直接改 dist），不要出队时才标记，否则同一格会被重复入队
     */
    public static int gridShortestPath(int[][] grid, int sr, int sc, int tr, int tc) {
        int m = grid.length, n = grid[0].length;
        if (grid[sr][sc] == 1 || grid[tr][tc] == 1) return -1;

        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, -1);
        dist[sr][sc] = 0;

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            if (r == tr && c == tc) return dist[r][c];   // 出队即最短，可以提前返回

            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] == 1 || dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[r][c] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }
        return -1;
    }

    /**
     * 0-1 BFS 变体：边权只有 0 和 1 时，用双端队列。权 0 的邻居放队首，权 1 的放队尾，
     * 队列始终保持 "距离单调"，仍是 O(V + E)，比上 Dijkstra 省一个 log。
     * adj.get(u) 里每个元素是 {v, w}，w ∈ {0, 1}
     */
    public static int[] zeroOneBFS(int n, List<List<int[]>> adj, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(src);

        while (!deque.isEmpty()) {
            int u = deque.pollFirst();
            for (int[] e : adj.get(u)) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (w == 0) deque.offerFirst(v);
                    else deque.offerLast(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        // 0 - 1 - 2
        // |       |
        // 3 ----- 4       5 孤立
        int n = 6;
        int[][] edges = {{0, 1}, {1, 2}, {0, 3}, {3, 4}, {2, 4}};
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        // 期望 [0, 1, 2, 1, 2, -1]
        System.out.println("邻接表 BFS: " + Arrays.toString(shortestPath(n, adj, 0)));

        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
        };
        // (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (2,1) -> (2,0)，期望 6
        System.out.println("网格 BFS: " + gridShortestPath(grid, 0, 0, 2, 0));
    }

}
