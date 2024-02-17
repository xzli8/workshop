package data_structure_algorithm.leetcode;

public class _79exist {

    public static class Solution0 {

        /**
         DFS
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



    public static class Solution1 {

        /**
         回溯
         */
         public boolean exist(char[][] board, String word) {
             this.board = board;
             this.m = board.length;
             this.n = board[0].length;
             this.word = word;
             boolean[][] visited = new boolean[m][n];

             for (int i = 0; i < m; i++) {
                 for (int j = 0; j < n; j++) {
                     backtrace(i, j, 0, visited);
                 }
             }
             return exist;
         }

         private char[][] board;
         private int m, n;
         private String word;
         private boolean exist;

         private void backtrace(int row, int col, int index, boolean[][] visited) {
             if (exist) return;
             if (index == word.length()) {
                 exist = true;
                 return;
             }
             if (row < 0 || row > m - 1 || col < 0 || col > n - 1
                 || visited[row][col] || board[row][col] != word.charAt(index)) return;

             visited[row][col] = true;
             backtrace(row - 1, col, index + 1, visited);
             backtrace(row + 1, col, index + 1, visited);
             backtrace(row, col - 1, index + 1, visited);
             backtrace(row, col + 1, index + 1, visited);
             visited[row][col] = false;
         }

    }



    public static class Solution2 {

        /**
         回溯
         */
        public boolean exist(char[][] board, String word) {
            // 初始化方向数组
            this.dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

            // 每个点都可能是起点
            this.m = board.length;
            this.n = board[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    backtrace(board, new boolean[m][n], word, i, j, 0);
                }
            }
            return exist;
        }

        private boolean exist;
        private int[][] dirs;
        private int m, n;

        private void backtrace(char[][] board, boolean[][] visited, String word, int row, int col, int idx) {
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
                backtrace(board, visited, word, row + dir[0], col + dir[1], idx + 1);
            }
            visited[row][col] = false;
        }

    }



    public static class Solution3 {

        /**
         DFS(回溯)
         */
        public boolean exist(char[][] board, String word) {
            // 初始化
            this.m = board.length; this.n = board[0].length; this.word = word;
            this.board = board; this.visited = new boolean[m][n];

            // 回溯
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        visited[i][j] = true;
                        if (backtrace(i, j, 1)) return true;
                        visited[i][j] = false;
                    }
                }
            }
            return false;
        }

        private int m, n;
        private String word;
        private char[][] board;
        private boolean[][] visited;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        private boolean backtrace(int x, int y, int index) {
            if (index == word.length()) return true;
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (inArea(xx, yy) && !visited[xx][yy] && board[xx][yy] == word.charAt(index)) {
                    visited[xx][yy] = true;
                    if (backtrace(xx, yy, index + 1)) return true;
                    visited[xx][yy] = false;
                }
            }
            return false;
        }

        private boolean inArea(int x, int y) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }

    }

}
