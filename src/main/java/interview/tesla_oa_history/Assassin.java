package interview.tesla_oa_history;

import org.junit.Test;

import java.util.*;

public class Assassin {

    /**
     * Given a 2D board of size m * n, each field of the board can be empty('.') may contain an obstacle('X') or may have
     * a character in it, the character might be either an assassin('A') or a guard, each guard stands still and looks straight
     * ahead, in the direction they are facing. Every guard looks in one of four directions (up, down, left, right) and represented
     * by one of the four symbols: '<'(left), '>'(right), '^'(up), 'v'(down), the guard can see everything in a straight line
     * in the direction in which they are facing, as far as the first obstacle of any other guard or the edge of the board.
     * The assassin can move from the current field to any other empty field with a sharped edge. The assassin cannot move
     * onto fields containing obstacles of enemies.
     * Write a function to find whether the assassin could reach the bottom right of the board.
     */

    public boolean canReachBottomRight(String[] board) {
        int m = board.length, n = board[0].length();

        int[] assassin = new int[2];
        Set<Integer> forbidden = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = board[i].charAt(j);
                if (c == 'A') {
                    assassin[0] = i;
                    assassin[1] = j;
                } else if (c == '<') {
                    for (int k = j - 1; k >= 0; k--) {
                        if (board[i].charAt(k) == '.' || board[i].charAt(k) == 'A') {
                            forbidden.add(i * n + k);
                        } else {
                            break;
                        }
                    }
                } else if (c == '>') {
                    for (int k = j + 1; k < n; k++) {
                        if (board[i].charAt(k) == '.' || board[i].charAt(k) == 'A') {
                            forbidden.add(i * n + k);
                        } else {
                            break;
                        }
                    }
                } else if (c == '^') {
                    for (int k = i - 1; k >= 0; k--) {
                        if (board[k].charAt(j) == '.' || board[k].charAt(j) == 'A') {
                            forbidden.add(k * n + j);
                        } else {
                            break;
                        }
                    }
                } else if (c == 'v') {
                    for (int k = i + 1; k < m; k++) {
                        if (board[k].charAt(j) == '.' || board[k].charAt(j) == 'A') {
                            forbidden.add(k * n + j);
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        if (forbidden.contains(assassin[0] * n + assassin[1])) {
            return false;
        }

        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(assassin);
        visited[assassin[0]][assassin[1]] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == m - 1 && cur[1] == n - 1) {
                return true;
            }
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (0 <= x && x < m && 0 <= y && y < n && (board[x].charAt(y) == '.' || board[x].charAt(y) == 'A')
                        && !forbidden.contains(x * n + y) && !visited[x][y]) {
                    q.offer(new int[] {x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(canReachBottomRight(new String[] {"...Xv", "AX..^", ".XX.."}));  // true
        System.out.println(canReachBottomRight(new String[] {"...", ">.A"}));   // false
        System.out.println(canReachBottomRight(new String[] {"A.v", "..."}));   // false
    }

}
