package data_structure_algorithm.supplyment;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {

    @Test
    public void test() {
        char[][] maps = new char[][] {{'S', '.'}, {'#', 'T'}};
        System.out.println(theMazeIV(maps));
    }

    /**
     BFS
     */
    public int theMazeIV(char[][] maps) {

        // 确定起始点的位置
        int[] start = new int[2], end = new int[2];
        int m = maps.length, n = maps[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (maps[i][j] == 'S') {
                    start = new int[] {i, j};
                }
                if (maps[i][j] == 'T') {
                    end = new int[] {i, j};
                }
            }
        }

        // 初始化BFS搜索队列
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.offer(start);
        visited[start[0]][start[1]] = true;

        // 初始化搜索方向
        int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 开始遍历
        int step = 0;
        while (!q.isEmpty()) {
            // 处理当前层
            int size = q.size();
            for (int i = 0; i < size; i++) {
                // 处理当前节点
                int[] cur = q.poll();

                // 是否到达终点
                if (cur[0] == end[0] && cur[1] == end[1]) {
                    return step;
                }

                // 访问相邻节点
                for (int[] dir : dirs) {
                    int x = cur[0], y = cur[1];
                    if (0 <= x + dir[0] && x + dir[0] < m
                            && 0 <= y + dir[1] && y + dir[1] < n
                            && maps[x + dir[0]][y + dir[1]] != '#') {
                        x += dir[0];
                        y += dir[1];
                    }

                    if (!visited[x][y]) {
                        q.offer(new int[] {x, y});
                        visited[x][y] = true;
                    }
                }
            }

            // 遍历完一层，步数+1
            step++;
        }

        // 无法到达终点，返回-1
        return -1;
    }

}
