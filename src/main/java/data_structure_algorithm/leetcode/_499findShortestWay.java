package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _499findShortestWay {

    /**
     *  题目链接：
     *      Leetcode490迷宫：https://www.lintcode.com/problem/787
     *      Leetcode505迷宫II：https://www.lintcode.com/problem/788
     *      Leetcode499迷宫III：https://zhuanlan.zhihu.com/p/141580934, https://www.cnblogs.com/lightwindy/p/9854182.html
     *      Lintcode1685迷宫IV：https://www.lintcode.com/problem/1685
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals("lul", findShortestWay(new int[][] {{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}}, new int[] {4, 3}, new int[] {0, 1}));
            Assert.assertEquals("impossible", findShortestWay(new int[][] {{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}}, new int[] {4, 3}, new int[] {3, 0}));
        }


        /**
         *  BFS
         */
        public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
            // 定义方向
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            char[] dirNotes = new char[] {'d', 'u', 'r', 'l'};

            // 初始化距离和路径
            int m = maze.length, n = maze[0].length;
            int[][] steps = new int[m][n];
            for (int i = 0; i < m; i++) Arrays.fill(steps[i], Integer.MAX_VALUE);
            steps[ball[0]][ball[1]] = 0;
            String[][] paths = new String[m][n];
            for (int i = 0; i < m; i++) Arrays.fill(paths[i], "");

            // BFS
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(ball);
            while (!q.isEmpty()) {
                // 处理当前层(要不要这一层循环都行)
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    // 处理当前节点
                    int[] cur = q.poll();

                    // 常规BFS需要在这里判断是否到达终点，但本题因为要找最小路径，所以需要遍历完整个搜索空间才行，因此这里无须判断

                    // 当前节点可以向上下左右四个方向移动
                    for (int j = 0; j < 4; j++) {
                        // 向某个方向移动，直到遇到边界或墙停止
                        int[] dir = dirs[j];
                        int x = cur[0], y = cur[1], step = 0;
                        while (0 <= x + dir[0] && x + dir[0] < m && 0 <= y + dir[1] && y + dir[1] < n && maze[x + dir[0]][y + dir[1]] != 1) {
                            x += dir[0];
                            y += dir[1];
                            step++;

                            // 注意：球经过洞时掉下去，这里需要break
                            if (x == hole[0] && y == hole[1]) break;
                        }

                        // 判断由当前路径到达停下来的点，所经过的步数，是不是比之前的少，如果是加入队列
                        step += steps[cur[0]][cur[1]];
                        String path = paths[cur[0]][cur[1]] + dirNotes[j];
                        if (step < steps[x][y]) {
                            steps[x][y] = step;
                            paths[x][y] = path;
                            q.offer(new int[] {x, y});
                        }
                        // 距离相同时，取字典序较小的路径
                        else if (step == steps[x][y] && !paths[x][y].isEmpty() && paths[x][y].compareTo(path) > 0) {
                            paths[x][y] = path;
                            q.offer(new int[] {x, y});
                        }
                    }
                }
            }
            return steps[hole[0]][hole[1]] == Integer.MAX_VALUE ? "impossible" : paths[hole[0]][hole[1]];
        }

    }

}
