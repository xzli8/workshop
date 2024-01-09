package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class _1293shortestPath {

    public static class Solution1 {

        /**
         BFS：用三元组(x, y, k)表明在位置(x, y)时最多还可以经过k个障碍物
         时间复杂度：O(M * N * K)
         空间复杂度：O(M * N * K)
         */
         public int shortestPath(int[][] grid, int k) {
             // 初始化
             int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
             int m = grid.length, n = grid[0].length;
             int[] start = new int[] {0, 0}, end = new int[] {m - 1, n - 1};
             Queue<int[]> q = new ArrayDeque<>();
             q.offer(start);
             boolean[][] visited = new boolean[m][n];
             visited[start[0]][start[1]] = true;
             int[][] rest = new int[m][n];
             rest[0][0] = k;
             int step = 0;

             // 遍历
             while (!q.isEmpty()) {
                 int size = q.size();
                 for (int i = 0; i < size; i++) {
                     int[] cur = q.poll();
                     if (cur[0] == end[0] && cur[1] == end[1]) return step;

                     for (int[] dir : dirs) {
                         int x = cur[0] + dir[0], y = cur[1] + dir[1];
                         if (0 <= x && x < m && 0 <= y && y < n && !visited[x][y]) {
                             // 这里会优先选择没有障碍物的路线，但没有障碍物的路线不一定是最短路线。
                             // 如果能控制优先往右下走，应该可以，但不知道怎么做？或者就是无法做到？
                             // example: grid = {[[0,0],[1,0],[1,0],[1,0],[1,0],[1,0],[0,0],
                             // [0,1],[0,1],[0,1],[0,0],[1,0],[1,0],[0,0]]}, k = 4
                             if (grid[x][y] == 0) {
                                 q.offer(new int[] {x, y});
                                 visited[x][y] = true;
                                 rest[x][y] = rest[cur[0]][cur[1]];
                             } else {
                                 if (rest[cur[0]][cur[1]] > 0) {
                                     q.offer(new int[] {x, y});
                                     visited[x][y] = true;
                                     rest[x][y] = rest[cur[0]][cur[1]] - 1;
                                 }
                             }
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
         BFS：用三元组(x, y, k)表明在位置(x, y)时最多还可以经过k个障碍物
         时间复杂度：O(M * N * K)
         空间复杂度：O(M * N * K)
         */
        public int shortestPath(int[][] grid, int k) {
            // 初始化
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int m = grid.length, n = grid[0].length;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[] {0, 0, k});
            boolean[][][] visited = new boolean[m][n][k + 1];   // 表示(x, y)还剩余k个消除障碍物的机会
            visited[0][0][k] = true;
            int step = 0;

            // 遍历
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    if (cur[0] == m - 1 && cur[1] == n - 1) return step;

                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (0 <= x && x < m && 0 <= y && y < n) {
                            if (grid[x][y] == 0 && !visited[x][y][cur[2]]) {
                                visited[x][y][cur[2]] = true;
                                q.offer(new int[] {x, y, cur[2]});
                            } else if (grid[x][y] == 1 && cur[2] > 0 && !visited[x][y][cur[2] - 1]) {
                                visited[x][y][cur[2] - 1] = true;
                                q.offer(new int[] {x, y, cur[2] - 1});
                            }
                        }
                    }
                }
                step++;
            }
            return -1;
        }

    }

}
