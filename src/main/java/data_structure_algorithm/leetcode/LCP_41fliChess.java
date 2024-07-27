package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

public class LCP_41fliChess {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(flipChess(new String[] {"....X.","....X.","XOOO..","......","......"}));
            System.out.println(flipChess(new String[] {".X.",".O.","XO."}));
            System.out.println(flipChess(new String[] {".......",".......",".......","X......",".O.....","..O....","....OOX"}));
        }

        /**
         BFS
         */
        public int flipChess(String[] chessboard) {
            // init
            m = chessboard.length;
            n = chessboard[0].length();
            board = new char[m][n];

            // bfs
            int maxCount = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (chessboard[i].charAt(j) != '.') continue;
                    maxCount = Math.max(maxCount, bfs(new int[] {i, j}, chessboard));
                }
            }
            return maxCount;
        }

        private int m, n;
        private char[][] board;
        private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1},
                {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        private int bfs(int[] startPos, String[] chessboard) {
            // init board every time
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = chessboard[i].charAt(j);
                }
            }

            // start
            int count = 0;
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(startPos);
            while (!q.isEmpty()) {
                int[] curPos = q.poll();
                for (int[] dir : dirs) {
                    int[] nextPos = move(curPos, dir);
                    while (isInside(nextPos) && isWhite(nextPos)) {
                        nextPos = move(nextPos, dir);
                    }

                    int[] pos = move(curPos, dir);
                    if (!samePos(pos, nextPos) && isInside(nextPos) && isBlack(nextPos)) {
                        for (int i = pos[0], j = pos[1]; !samePos(new int[] {i, j}, nextPos); i += dir[0], j += dir[1]) {
                            board[i][j] = 'X';
                            q.offer(new int[] {i, j});
                            count++;
                        }
                    }
                }
            }
            return count;
        }

        private boolean isInside(int[] pos) {
            return 0 <= pos[0] && pos[0] < m && 0 <= pos[1] && pos[1] < n;
        }

        private boolean isWhite(int[] pos) {
            return board[pos[0]][pos[1]] == 'O';
        }

        private boolean isBlack(int[] pos) {
            return board[pos[0]][pos[1]] == 'X';
        }

        private boolean samePos(int[] pos1, int[] pos2) {
            return pos1[0] == pos2[0] && pos1[1] == pos2[1];
        }

        private int[] move(int[] pos, int[] dir) {
            return new int[] {pos[0] + dir[0], pos[1] + dir[1]};
        }

    }

}
