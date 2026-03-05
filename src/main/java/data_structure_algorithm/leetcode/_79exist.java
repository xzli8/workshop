package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _79exist {

    public static class Solution1 {

        /**
         BFS:
         Note: Error! Todo
         */
        public boolean exist(char[][] board, String word) {
            int m = board.length, n = board[0].length;

            boolean[][] visited = new boolean[m][n];    // default false
            Queue<int[]> q = new ArrayDeque<>();
            int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        visited[i][j] = true;
                        q.offer(new int[] {i, j, 1});
                        while (!q.isEmpty()) {
                            int[] cur = q.poll();
                            int k = cur[2]; // 当前字符索引
                            if (k == word.length()) return true; // 找到完整单词
                            for (int[] dir : dirs) {
                                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                                if (0 <= x && x < m && 0 <= y && y < n && !visited[x][y] && board[x][y] == word.charAt(k)) {
                                    visited[x][y] = true;
                                    q.offer(new int[] {x, y, k + 1});
                                }
                            }
                        }
                        visited[i][j] = false;
                    }
                }
            }
            return false;
        }

    }


    public static class Solution2_1 {

        /**
         DFS:
         Note: dfs函数有返回值boolean，找到解就立马返回true，能最快返回但不能找到所有解。可以用这种方式找到一条路径。
         */
        public boolean exist(char[][] board, String word) {
            this.word = word;
            this.m = board.length;
            this.n = board[0].length;
            this.board = board;
            this.visited = new boolean[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        visited[i][j] = true;
                        if (dfs(i, j, 1)) return true;
                        visited[i][j] = false;
                    }
                }
            }
            return false;
        }

        private String word;
        private int m, n;
        private char[][] board;
        private boolean[][] visited;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private boolean dfs(int x, int y, int idx) {
            if (idx == word.length()) return true;
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (inArea(xx, yy) && !visited[xx][yy] && word.charAt(idx) == board[xx][yy]) {
                    visited[xx][yy] = true;
                    if (dfs(xx, yy, idx + 1)) return true;
                    visited[xx][yy] = false;
                }
            }
            return false;
        }

        private boolean inArea(int x, int y) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }

    }


    public static class Solution2_2 {

        /**
         DFS
         Note: dfs函数没返回值，依靠全局变量判断最终结果，需要遍历完所有结果才返回。可以用这种方式找到所有路径。
         */
        public boolean exist(char[][] board, String word) {
            // 初始化方向数组
            this.dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // 每个点都可能是起点
            this.m = board.length;
            this.n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    dfs(board, new boolean[m][n], word, i, j, 0);
                }
            }
            return exist;
        }

        private boolean exist;
        private int[][] dirs;
        private int m, n;

        private void dfs(char[][] board, boolean[][] visited, String word, int row, int col, int idx) {
            if (exist) return;
            if (idx == word.length()) {
                exist = true;
                return;
            }
            // 越界直接返回
            if (row < 0 || row >= m || col < 0 || col >= n) return;
            // 访问过或不相等直接返回
            if (visited[row][col] || board[row][col] != word.charAt(idx)) return;

            visited[row][col] = true;
            for (int[] dir : dirs) {
                dfs(board, visited, word, row + dir[0], col + dir[1], idx + 1);
            }
            visited[row][col] = false;
        }

    }


}
