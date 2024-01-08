package data_structure_algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _130solve {

    public static class Solution1 {

        /**
         DFS：(逆向思考)从边界出发，将所有与O相邻的区域标记为"A"，然后再遍历整个区域，将"O"标记为"X"，将"A"标记为"O"
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public void solve(char[][] board) {
            // 初始化
            this.m = board.length; this.n = board[0].length; this.board = board;

            // 遍历并标记
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        dfs(i, j);
                    }
                }
            }

            // 遍历整个区域，将"O"标记成"X"，将"A"标记成"O"
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private int m, n;
        private char[][] board;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void dfs(int i, int j) {
            if (!inArea(i, j) || board[i][j] != 'O') return;
            board[i][j] = 'A';
            for (int[] dir : dirs) {
                dfs(i + dir[0], j + dir[1]);
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }



    public static class Solution2 {

        /**
         BFS：(逆向思考)从边界出发，将所有与O相邻的区域标记为"A"，然后再遍历整个区域，将"O"标记为"X"，将"A"标记为"O"
         时间复杂度：O(M * N)
         空间复杂度：O(M * N)
         */
        public void solve(char[][] board) {
            // 初始化
            this.m = board.length; this.n = board[0].length; this.board = board;

            // 遍历并标记
            int m = board.length, n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        bfs(i, j);
                    }
                }
            }

            // 遍历整个区域，将"O"标记成"X"，将"A"标记成"O"
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                    if (board[i][j] == 'A') {
                        board[i][j] = 'O';
                    }
                }
            }
        }

        private int m, n;
        private char[][] board;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private void bfs(int i, int j) {
            if (board[i][j] != 'O') return;

            Queue<int[]> q = new LinkedList<>();
            board[i][j] = 'A';
            q.offer(new int[] {i, j});
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (inArea(x, y) && board[x][y] == 'O') {
                        board[x][y] = 'A';
                        q.offer(new int[] {x, y});
                    }
                }
            }
        }

        private boolean inArea(int i, int j) {
            return 0 <= i && i < m && 0 <= j && j < n;
        }


    }


}
