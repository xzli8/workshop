package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _505shortestDistance {

    /**
     *  题目链接：
     *      Leetcode490迷宫：https://www.lintcode.com/problem/787
     *      Leetcode505迷宫II：https://www.lintcode.com/problem/788
     *      Leetcode499迷宫III：https://zhuanlan.zhihu.com/p/141580934, https://www.cnblogs.com/lightwindy/p/9854182.html
     *      Lintcode1685迷宫IV：https://www.lintcode.com/problem/1685
     */

    public static class Solution1 {

        /**
         BFS：遍历全部搜索空间，找到最短路径
         */
        public int shortestDistance(int[][] maze, int[] start, int[] destination) {

            // 初始化搜索队列
            int m = maze.length, n = maze[0].length;
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {start[0], start[1]});

            // 初始化距离数组，记录从起点开始到每个节点的距离
            int[][] steps = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    steps[i][j] = Integer.MAX_VALUE;
                }
            }
            steps[start[0]][start[1]] = 0;

            // 初始化搜索方向
            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            // 开始遍历
            while (!q.isEmpty()) {
                // 处理当前层
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    // 处理当前节点
                    int[] cur = q.poll();

                    // 常规BFS需要在这里判断是否到达终点，但本题因为要找最小路径，所以需要遍历完整个搜索空间才行，因此这里无须判断

                    // 当前节点可以向上下左右四个方向移动
                    for (int[] dir : dirs) {
                        // 向某个方向移动，直到遇到边界或墙停止
                        int x = cur[0], y = cur[1], step = 0;
                        while (0 <= x + dir[0] && x + dir[0] < m
                                && 0 <= y + dir[1] && y + dir[1] < n
                                && maze[x + dir[0]][y + dir[1]] == 0) {
                            x += dir[0];
                            y += dir[1];
                            step++;
                        }

                        // 判断由当前路径到达停下来的点，所经过的步数，是不是比之前的少，如果是加入队列
                        if (steps[cur[0]][cur[1]] + step < steps[x][y]) {
                            steps[x][y] = steps[cur[0]][cur[1]] + step;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }

            // 球无法停留在目的地，返回-1
            return steps[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : steps[destination[0]][destination[1]];
        }

    }

}
