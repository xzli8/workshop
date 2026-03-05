package data_structure_algorithm.algorithm.graph;

import java.util.Arrays;

/**
 * Floyd-Warshall 最短路 —— 所有点对（全源）
 *
 * 核心：动态规划。枚举中转点 k，对每一对 (i, j) 尝试 "先到 k 再到 j" 是否更短：
 *          dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
 *      三层循环，k 必须放在最外层：外层 k 的含义是 "只允许用前 k 个点做中转"，
 *      放里面就破坏了这个状态定义。
 *
 * 允许负权边，不允许负权环。跑完后若 dist[i][i] < 0，说明 i 在某个负环上。
 *
 * 时间：O(V^3)    空间：O(V^2)
 * 只适合 V 在几百以内的小图。它的优势是一次算完所有点对，之后查询 O(1)，
 * 并且代码极短，几乎不会写错。
 *
 * 典型题：1334 阈值距离内邻居最少的城市、1462 课程表 IV（传递闭包，把 min/+ 换成 or/and）
 */
public class FloydWarshall {

    private static final int INF = Integer.MAX_VALUE / 2;

    /**
     * edges 每个元素是 {u, v, w}，表示 u -> v 权重 w。无向图调用方自己加两条。
     * 返回 n x n 的距离矩阵，不可达为 INF；存在负环返回 null
     */
    public static int[][] allPairsShortestPath(int n, int[][] edges) {
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            dist[u][v] = Math.min(dist[u][v], w);   // 重边取最小
        }

        for (int k = 0; k < n; k++) {               // 中转点，必须最外层
            for (int i = 0; i < n; i++) {
                if (dist[i][k] == INF) continue;    // i 到不了 k，整行跳过，顺便防溢出
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dist[i][i] < 0) return null;        // 负环
        }
        return dist;
    }

    /**
     * 传递闭包变体（1462 课程表 IV）：reach[i][j] 表示 i 能否到达 j
     */
    public static boolean[][] transitiveClosure(int n, int[][] edges) {
        boolean[][] reach = new boolean[n][n];
        for (int i = 0; i < n; i++) reach[i][i] = true;
        for (int[] e : edges) reach[e[0]][e[1]] = true;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                if (!reach[i][k]) continue;
                for (int j = 0; j < n; j++) {
                    if (reach[k][j]) reach[i][j] = true;
                }
            }
        }
        return reach;
    }

    public static void main(String[] args) {
        // 1334 题样例：n=4, edges=[[0,1,3],[1,2,1],[1,3,4],[2,3,1]]（无向），threshold=4
        int n = 4;
        int[][] directed = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int[][] edges = new int[directed.length * 2][];
        for (int i = 0; i < directed.length; i++) {
            int[] e = directed[i];
            edges[2 * i] = new int[]{e[0], e[1], e[2]};
            edges[2 * i + 1] = new int[]{e[1], e[0], e[2]};
        }
        int[][] dist = allPairsShortestPath(n, edges);
        for (int[] row : dist) System.out.println(Arrays.toString(row));

        // 找 "阈值 4 以内可达城市最少" 的城市，平局取编号大的，期望 3
        int threshold = 4, best = -1, bestCount = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && dist[i][j] <= threshold) cnt++;
            }
            if (cnt <= bestCount) {
                bestCount = cnt;
                best = i;
            }
        }
        System.out.println("1334 答案(期望 3): " + best);
    }

}
