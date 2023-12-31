package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _785isBipartite {

    public static class Solution1 {


        /**
         二分图：转换为染色问题，将同一条边上的两个点染成不同的颜色，可否有解
         BFS or DFS
         */

        private boolean isBipartite = true;
        private int n;
        private boolean[] visited, color;

        public boolean isBipartite(int[][] graph) {

            // 初始化相关变量
            n = graph.length;
            visited = new boolean[n];
            color = new boolean[n];

            // 因为图可能不是连通图，所以需要遍历所有可能的起点
            for (int i = 0; i < n; i++) {
                if (isBipartite && !visited[i]) {
                    // dfs
                    visited[i] = true;
                    dfs(graph, i);

                    // bfs
                    // bfs(graph, i);
                }
            }
            return isBipartite;
        }

        private void bfs(int[][] graph, int start) {

            // 初始化搜索队列
            Queue<Integer> q = new LinkedList<>();
            q.offer(start);
            visited[start] = true;
            color[start] = true;

            // 开始遍历
            while (!q.isEmpty() && isBipartite) {
                // 处理当前层(本题可以不需要这一步，因为最终要遍历完整个搜索空间)
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    // 处理当前节点
                    int cur = q.poll();

                    // 访问当前节点的相邻节点
                    for (int adj : graph[cur]) {
                        // 如果相邻节点没有被访问过，那么将其染色为当前节点不一样的颜色
                        if (!visited[adj]) {
                            q.offer(adj);
                            visited[adj] = true;
                            color[adj] = !color[cur];
                        } else {
                            // 如果相邻节点被访问过，并且颜色与当前节点相同，返回false;
                            if (color[adj] == color[cur]) {
                                isBipartite = false;
                                return;
                            }
                        }
                    }
                }
            }
        }

        private void dfs(int[][] graph, int start) {
            if (!isBipartite) return;

            for (int adj : graph[start]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    color[adj] = !color[start];
                    dfs(graph, adj);
                } else {
                    if (color[adj] == color[start]) {
                        isBipartite = false;
                        return;
                    }
                }
            }
        }

    }

}
