package data_structure_algorithm.leetcode;

public class _79exist {

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

}
