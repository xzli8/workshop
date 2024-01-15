package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _886possibleBipartition {

    public static class Solution1 {

        /**
         二分图：DFS or BFS
         */

        private boolean isBiPartite = true;
        private boolean[] visited, color;

        public boolean possibleBipartition(int n, int[][] dislikes) {

            // 构建图
            List<Integer>[] graph = buildGraph(n, dislikes);

            // 初始化相关变量
            visited = new boolean[n];
            color = new boolean[n];

            // 因为图可能不连通，所以需要遍历所有可能的起点
            for (int i = 0; i < n; i++) {
                if (isBiPartite && !visited[i]) {
                    // dfs
                    // visited[i] = true;
                    // dfs(graph, i);

                    // bfs;
                    bfs(graph, i);
                }
            }
            return isBiPartite;
        }

        // 构建图
        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            List<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int[] dislike : dislikes) {
                // 因为人的编号从1开始，所以这里需要-1
                int start = dislike[0] - 1;
                int end = dislike[1] - 1;

                // start的相邻节点加上end，并且end的相邻节点加上start
                graph[start].add(end);
                graph[end].add(start);
            }
            return graph;
        }

        private void dfs(List<Integer>[] graph, int start) {
            if (!isBiPartite) return;

            for (int adj : graph[start]) {
                if (!visited[adj]) {
                    visited[adj] = true;
                    color[adj] = !color[start];
                    dfs(graph, adj);
                } else {
                    if (color[adj] == color[start]) {
                        isBiPartite = false;
                        return;
                    }
                }
            }
        }

        private void bfs(List<Integer>[] graph, int start) {
            // 初始化搜索队列
            Queue<Integer> q = new LinkedList<>();
            q.offer(start);
            visited[start] = true;

            // 开始遍历
            while (!q.isEmpty() && isBiPartite) {
                int cur = q.poll();
                for (int adj : graph[cur]) {
                    if (!visited[adj]) {
                        visited[adj] = true;
                        color[adj] = !color[cur];
                        q.offer(adj);
                    } else {
                        if (color[adj] == color[cur]) {
                            isBiPartite = false;
                            return;
                        }
                    }
                }
            }
        }

    }

}
