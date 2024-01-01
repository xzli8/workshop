package data_structure_algorithm.leetcode;

import java.util.PriorityQueue;

public class _407trapRainWater {

    public static class Solution1 {

        /**
         DP + 分维度计算取最小值
         ERROR：不能简单的分维度计算然后取最小值，因为水可以在各个方向流动，仅从x、y方向限制住没有用
         */
         public int trapRainWater(int[][] heightMap) {
             int m = heightMap.length, n = heightMap[0].length;

             // 计算x方向上的leftMax和rightMax
             int[][][] heightMaxX = new int[m][2][n];
             for (int i = 0; i < m; i++) {
                 heightMaxX[i] = trap(heightMap[i]);
             }

             // 计算y方向上的leftMax和rightMax
             int[][][] heightMaxY = new int[n][2][m];
             int[] heightY = new int[m];
             for (int j = 0; j < n; j++) {
                 for (int i = 0; i < m; i++) heightY[i] = heightMap[i][j];
                 heightMaxY[j] = trap(heightY);
             }

             // 遍历所有网格，计算总和
             int sum = 0;
             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     int minHeightX = Math.min(heightMaxX[i][0][j], heightMaxX[i][1][j]);
                     int minHeightY = Math.min(heightMaxY[j][0][i], heightMaxY[j][1][i]);
                     int minHeight = Math.min(minHeightX, minHeightY);
                     int diff = minHeight - heightMap[i][j];
                     if (diff > 0) sum += diff;
                 }
             }
             return sum;
         }

         /**
             动态规划(42.接雨水)
          */
         private int[][] trap(int[] height) {
             int n = height.length;
             int[][] res = new int[2][n];    // res[0]表示leftMax，res[1]表示rightMax
             res[0][0] = 0;
             for (int i = 1; i < n; i++) {
                 res[0][i] = Math.max(res[0][i - 1], height[i - 1]);
             }
             res[1][n - 1] = 0;
             for (int i = n - 2; i >= 0; i--) {
                 res[1][i] = Math.max(res[1][i + 1], height[i + 1]);
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         最小堆：二维情况下，我们维护左右两边两个最高的墙，那么在这里就需要维护周围的一个圈，用最小堆找圈中最小元素
         时间复杂度：O(M * N * log(M*N))
         空间复杂度：O(M * N)
         */
        public int trapRainWater(int[][] heightMap) {
            int m = heightMap.length, n = heightMap[0].length;
            boolean[][] visited = new boolean[m][n];
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);   // key是编号，value是高度

            // 将最外层先加入堆
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        pq.offer(new int[] {i * n + j, heightMap[i][j]});
                        visited[i][j] = true;
                    }
                }
            }

            // 开始遍历
            int sum = 0;
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] / n + dir[0], y = cur[0] % n + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        visited[x][y] = true;
                        pq.offer(new int[] {x * n + y, Math.max(heightMap[x][y], cur[1])});
                        if (cur[1] > heightMap[x][y]) sum += cur[1] - heightMap[x][y];
                    }
                }
            }
            return sum;
        }

    }

}
