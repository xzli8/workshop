package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _1091shortestPathBinaryMatrix {

    public static class Solution1 {

        /**
         BFS
         */
        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid[0][0] == 1) return -1;

            int n = grid.length, step = 1;
            int[][] dirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
            boolean[][] visited = new boolean[n][n];

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, 0});
            visited[0][0] = true;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    if (cur[0] == n - 1 && cur[1] == n - 1) {
                        return step;
                    }
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (0 <= x && x < n && 0 <= y && y < n && grid[x][y] == 0 && !visited[x][y]) {
                            q.offer(new int[] {x, y});
                            visited[x][y] = true;
                        }
                    }
                }
                step++;
            }
            return -1;
        }

    }



    public static class Solution2 {

        /**
         BFS(这种解法更类似于Dijkstra算法)
         */
        public int shortestPathBinaryMatrix(int[][] grid) {
            if (grid[0][0] == 1) return -1;

            int n = grid.length;
            int[][] dirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, 0});
            dist[0][0] = 1;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                if (cur[0] == n - 1 && cur[1] == n - 1) {
                    return dist[cur[0]][cur[1]];
                }
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (0 <= x && x < n && 0 <= y && y < n && grid[x][y] == 0
                            && dist[x][y] > dist[cur[0]][cur[1]] + 1) {
                        q.offer(new int[] {x, y});
                        dist[x][y] = dist[cur[0]][cur[1]] + 1;
                    }
                }
            }
            return -1;
        }

    }

}
