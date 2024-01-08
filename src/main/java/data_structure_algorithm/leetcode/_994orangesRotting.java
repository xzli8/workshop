package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _994orangesRotting {

    public static class Solution1 {

        /**
         BFS：多源BFS
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int orangesRotting(int[][] grid) {
            // 初始化
            int m = grid.length, n = grid[0].length, sum = 0;
            Queue<int[]> q = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 2) {
                        q.offer(new int[] {i, j});
                    }
                    sum += grid[i][j];
                }
            }
            if (sum == 0) return 0; // 这里需要一个特殊判断，网格中没有任何橘子的时候直接返回0(input:[[0]], output:0)

            // 遍历
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int step = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int k = 0; k < size; k++) {
                    int[] cur = q.poll();
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (0 <= x && x < m && 0 <= y && y < n && grid[x][y] == 1) {
                            grid[x][y] = 2;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
                step++;
            }

            // 检查是否所有的橘子都被腐烂
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        return -1;
                    }
                }
            }

            // 处理完最后一层后又+1，所以这里要-1
            return step - 1;
        }


    }

}
