package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _490hasPath {

    /**
     *  题目链接：
     *      Leetcode490迷宫：https://www.lintcode.com/problem/787
     *      Leetcode505迷宫II：https://www.lintcode.com/problem/788
     *      Leetcode499迷宫III：https://zhuanlan.zhihu.com/p/141580934, https://www.cnblogs.com/lightwindy/p/9854182.html
     *      Lintcode1685迷宫IV：https://www.lintcode.com/problem/1685
     */

    public static class Solution1 {

        /**
         BFS：
         Note: 与DFS相比，BFS可以找到最短路径后立马返回
         */
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {

            // 初始化BFS搜索队列
            int m = maze.length, n = maze[0].length;
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[m][n];
            q.offer(start);
            visited[start[0]][start[1]] = true;

            // 定义方向数组
            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            // 开始遍历
            while (!q.isEmpty()) {

                // 处理当前层
                int size = q.size();
                for (int k = 0; k < size; k++) {

                    // 处理当前节点
                    int[] cur = q.poll();

                    // 判断是否到达终点
                    if (cur[0] == destination[0] && cur[1] == destination[1]) {
                        return true;
                    }

                    // 该节点可以选择任意一个方向移动
                    for (int[] dir : dirs) {
                        // 移动直到遇到墙或者边界才停下来
                        int x = cur[0];
                        int y = cur[1];
                        while (0 <= x + dir[0] && x + dir[0] < m
                                && 0 <= y + dir[1] && y + dir[1] < n
                                && maze[x + dir[0]][y + dir[1]] == 0) {
                            x += dir[0];
                            y += dir[1];
                        }

                        // 判断停下来的这个点是否访问过，如果未访问过，加入队列
                        if (!visited[x][y]) {
                            q.offer(new int[] {x, y});
                            visited[x][y] = true;
                        }
                    }

                }
            }

            // 遍历完了都还没到终点，返回false
            return false;
        }

    }


    public static class Solution2 {

        /**
         * DFS
         * Note: DFS也可以，但需要遍历完全部节点后返回visited[destination[0]][destination[1]]，不能像BFS那样找到了最短路径就立马返回
         */

    }

}
