package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class _212findWords {

    public static class Solution1 {

        /**
         回溯
         */
        public List<String> findWords(char[][] board, String[] words) {
            // 初始化
            this.m = board.length;
            this.n = board[0].length;
            this.dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            this.targets = Arrays.stream(words).collect(Collectors.toSet());

            // 回溯
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    backtrace(board, new boolean[m][n], i, j, new StringBuilder());
                }
            }
            return res;
        }

        private int m, n;
        private int[][] dirs;
        private Set<String> targets;
        private List<String> res = new ArrayList<>();

        private void backtrace(char[][] board, boolean[][] visited, int row, int col, StringBuilder path) {
            if (path.length() > 10) return; // 题目规定word.length <= 10，这里需要剪枝，否则会超时
            if (targets.contains(path.toString())) {
                res.add(path.toString());
                targets.remove(path.toString());
                // 注意这里不能return
            }
            if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) return;

            visited[row][col] = true;
            path.append(board[row][col]);
            for (int[] dir : dirs) {
                backtrace(board, visited, row + dir[0], col + dir[1], path);
            }
            path.deleteCharAt(path.length() - 1);
            visited[row][col] = false;
        }

    }

}
