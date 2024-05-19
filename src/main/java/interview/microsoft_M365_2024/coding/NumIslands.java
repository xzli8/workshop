package interview.microsoft_M365_2024.coding;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumIslands {


    /**
     * Date: 2024.04.01
     * Solution: DFS/BFS (要求用BFS)
     */

    public int numIslands(char[][] grid) {
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length, n = grid[0].length, nums = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    q.offer(new int[] {i, j});
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        for (int[] dir : dirs) {
                            int x = cur[0] + dir[0], y = cur[1] + dir[1];
                            if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == '1') {
                                grid[x][y] = '2';
                                q.offer(new int[] {x, y});
                            }
                        }
                    }
                    nums++;
                }
            }
        }
        return nums;
    }

}
