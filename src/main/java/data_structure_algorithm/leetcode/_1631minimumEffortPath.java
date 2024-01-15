package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _1631minimumEffortPath {

    public static class Solution1 {

        /**
         dijkstra
         */
        public int minimumEffortPath(int[][] heights) {

            // 初始化体力耗费数组
            int m = heights.length, n = heights[0].length;
            int[][] costs = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(costs[i], Integer.MAX_VALUE);
            }
            costs[0][0] = 0;

            // 定义方向数组
            int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

            // 使用优先队列开始遍历，体力耗费最少的排在最前面
            PriorityQueue<State> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
            pq.offer(new State(0, 0, 0));
            while (!pq.isEmpty()) {
                // 处理当前节点
                State cur = pq.poll();
                if (cur.cost > costs[cur.x][cur.y]) {
                    continue;
                }

                // 访问相邻节点
                for (int[] dir : dirs) {
                    // 计算相邻节点坐标
                    int x = cur.x + dir[0], y = cur.y + dir[1];

                    // 判断是否越界
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }

                    // 计算体力耗费
                    int cost = Math.max(cur.cost, Math.abs(heights[cur.x][cur.y] - heights[x][y]));
                    if (cost < costs[x][y]) {
                        costs[x][y] = cost;
                        pq.offer(new State(x, y, cost));
                    }
                }
            }
            return costs[m-1][n-1];
        }

        class State {
            int x, y, cost;
            State (int x, int y, int cost) {
                this.x = x;
                this.y = y;
                this.cost = cost;
            }
        }

    }

}
