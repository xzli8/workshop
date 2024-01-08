package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _827largestIsland {

    public static class Solution1 {

        /**
         DFS：先遍历每个岛屿，标记并计算每个岛屿的面积，然后遍历海洋，计算最大人工岛
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public int largestIsland(int[][] grid) {
            // 初始化
            this.m = grid.length; this.n = grid[0].length; this.grid = grid;
            int islandIdx = 2;    // 岛屿编号从2开始，因为0和1分别代表海洋和陆地

            // 遍历岛屿，标记并计算
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        islandIdx2Area.put(islandIdx, islandArea(i, j, islandIdx++));
                    }
                }
            }

            // 遍历海洋，计算最大人工岛的面积
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        int area = 1;
                        Set<Integer> visited = new HashSet<>();
                        for (int[] dir : dirs) {
                            int x = i + dir[0], y = j + dir[1];
                            if (inArea(x, y) && islandIdx2Area.containsKey(grid[x][y]) && !visited.contains(grid[x][y])) {
                                visited.add(grid[x][y]);
                                area += islandIdx2Area.get(grid[x][y]);
                            }
                        }
                        maxArea = Math.max(maxArea, area);
                    }
                }
            }
            // maxArea为0意味着前面遍历海洋时没有更新maxArea，但遍历就一定会更新，由此可知没有遍历，也就是没有海洋，直接返回陆地面积
            return maxArea == 0 ? m * n : maxArea;
        }

        private int m, n;
        private int[][] grid;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        private Map<Integer, Integer> islandIdx2Area = new HashMap<>();

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }

        private int islandArea(int i, int j, int islandIdx) {
            if (!inArea(i, j) || grid[i][j] != 1) return 0;
            grid[i][j] = islandIdx;
            int area = 1;
            for (int[] dir : dirs) {
                area += islandArea(i + dir[0], j + dir[1], islandIdx);
            }
            return area;
        }

    }

}
