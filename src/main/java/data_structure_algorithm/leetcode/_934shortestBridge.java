package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class _934shortestBridge {

    public static class Solution1 {

        /**
         DFS(BFS) + BFS: 第一步标记 + 第二步找最短路径，O(N^2), O(N^2)
         */
        public int shortestBridge(int[][] grid) {
            int n = grid.length;
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        Queue<int[]> q = new ArrayDeque<int[]>();
                        dfs(i, j, grid, q);

                        int step = 0;
                        while (!q.isEmpty()) {
                            int size = q.size();
                            for (int k = 0; k < size; k++) {
                                int[] cur = q.poll();
                                for (int[] dir : dirs) {
                                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                                    if (x < 0 || x >= n || y < 0 || y >= n) continue;
                                    if (grid[x][y] == 0) {
                                        q.offer(new int[]{x, y});
                                        grid[x][y] = -1;
                                    } else if (grid[x][y] == 1) {
                                        return step;
                                    }
                                }
                            }
                            step++;
                        }
                    }
                }
            }
            return 0;
        }

        private void dfs(int x, int y, int[][] grid, Queue<int[]> q) {
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != 1) {
                return;
            }
            q.offer(new int[] {x, y});
            grid[x][y] = -1;
            dfs(x - 1, y, grid, q);
            dfs(x + 1, y, grid, q);
            dfs(x, y - 1, grid, q);
            dfs(x, y + 1, grid, q);
        }

    }


    public static class Solution2 {

        // 多个岛，对每个岛做多源BFS向外扩散。时间复杂度：O(M*N)
        public int shortestDistance(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
            int[][] owner = new int[m][n];   // 0=未占领；否则=占领它的岛 id
            int[][] dist  = new int[m][n];   // 距各自岛的步数
            Deque<int[]> q = new ArrayDeque<>();

            // 标号 + 把所有陆地格作为多源起点（dist=0）一次性入队
            int id = 1;
            for (int r = 0; r < m; r++)
                for (int c = 0; c < n; c++)
                    if (grid[r][c] == 1 && owner[r][c] == 0) {
                        id++;
                        Deque<int[]> st = new ArrayDeque<>();
                        st.push(new int[]{r, c}); owner[r][c] = id;
                        while (!st.isEmpty()) {                  // flood fill 标号本岛
                            int[] cur = st.pop();
                            q.offer(cur);                        // 同时作为 BFS 源
                            for (int[] d : dirs) {
                                int nr=cur[0]+d[0], nc=cur[1]+d[1];
                                if (nr>=0&&nr<m&&nc>=0&&nc<n && grid[nr][nc]==1 && owner[nr][nc]==0) {
                                    owner[nr][nc] = id; st.push(new int[]{nr, nc});
                                }
                            }
                        }
                    }

            // 多源BFS
            int ans = Integer.MAX_VALUE;
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r=cur[0], c=cur[1], oid=owner[r][c], dd=dist[r][c];
                for (int[] d : dirs) {
                    int nr=r+d[0], nc=c+d[1];
                    if (nr<0||nr>=m||nc<0||nc>=n) continue;
                    if (owner[nr][nc] == 0) {                    // 未占领的水，本波占领
                        owner[nr][nc] = oid; dist[nr][nc] = dd+1;
                        q.offer(new int[]{nr, nc});
                    } else if (owner[nr][nc] != oid) {           // 撞上别的岛的水波
                        ans = Math.min(ans, dd + dist[nr][nc]);
                    }
                }
            }
            return ans == Integer.MAX_VALUE ? -1 : ans;
        }

    }

}
