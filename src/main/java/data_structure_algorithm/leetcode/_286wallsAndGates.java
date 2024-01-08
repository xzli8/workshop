package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _286wallsAndGates {

    /**
     * 题目链接：https://www.lintcode.com/problem/663/description
     */

    public static class Solution1 {

        /**
         BFS：(多源BFS)从门出发开始遍历，找到最近的房间
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public void wallsAndGates(int[][] rooms) {
            // 初始化
            int INF = 2147483647;
            int m = rooms.length, n = rooms[0].length;
            Queue<int[]> q = new ArrayDeque<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (rooms[i][j] == 0) {
                        q.offer(new int[] {i, j});
                    }
                }
            }

            // 遍历
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            int step = 0;
            while (!q.isEmpty()) {
                step++;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    for (int[] dir : dirs) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (0 <= x && x < m && 0 <= y && y < n && rooms[x][y] == INF) {
                            rooms[x][y] = step;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
        }


    }

}
