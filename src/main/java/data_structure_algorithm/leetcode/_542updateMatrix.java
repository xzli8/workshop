package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _542updateMatrix {

    public static class Solution1 {

        // MS-BFS: O(N), O(N)
        public int[][] updateMatrix(int[][] mat) {
            int m = mat.length, n = mat[0].length;
            boolean[][] visited = new boolean[m][n];
            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        q.offer(new int[] {i, j});
                        visited[i][j] = true;
                    }
                }
            }

            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            int step = 1;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (0 <= x && x < m && 0 <= y && y < n && mat[x][y] == 1 && !visited[x][y]) {
                            visited[x][y] = true;
                            q.offer(new int[] {x, y});
                            mat[x][y] = step;
                        }
                    }
                }
                step++;
            }
            return mat;
        }

    }

}
