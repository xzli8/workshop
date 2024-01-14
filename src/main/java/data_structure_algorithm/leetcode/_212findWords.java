package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class _212findWords {

    public static class Solution1 {

        public List<String> findWords(char[][] board, String[] words) {
            // 初始化
            this.m = board.length; this.n = board[0].length;
            this.board = board; this.visited = new boolean[m][n];
            this.dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            this.targets = Arrays.stream(words).collect(Collectors.toSet());

            // 回溯
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j] = true;
                    path.append(board[i][j]);
                    backtrace(i, j, path);
                    visited[i][j] = false;
                    path.deleteCharAt(path.length() - 1);
                }
            }
            return res;
        }

        private int m, n;
        private char[][] board;
        private boolean[][] visited;
        private int[][] dirs;
        private Set<String> targets;
        private List<String> res = new ArrayList<>();

        private void backtrace(int x, int y, StringBuilder path) {
            if (path.length() > 10) return; // 题目规定word.length <= 10，这里可以剪枝(但仍然会超时)
            if (targets.contains(path.toString())) {
                res.add(path.toString());
                targets.remove(path.toString());
                // 注意这里不能return，需要继续搜索
            }

            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (inArea(xx, yy) && !visited[xx][yy]) {
                    visited[xx][yy] = true;
                    path.append(board[xx][yy]);
                    backtrace(xx, yy, path);
                    path.deleteCharAt(path.length() - 1);
                    visited[xx][yy] = false;
                }
            }
        }

        private boolean inArea(int x, int y) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }

    }



    public static class Solution2 {

        /**
         回溯 + 字典树：单纯的回溯会遍历四个方向，可以先构建字典树，然后用字典树判断提前剪枝
         */
        public List<String> findWords(char[][] board, String[] words) {
            // 构建字典树
            for (String word : words) insert(word);

            // 回溯
            this.m = board.length; this.n = board[0].length;
            this.board = board; this.visited = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int index = board[i][j] - 'a';
                    if (root.children[index] != null) {
                        visited[i][j] = true;
                        backtrace(i, j, root.children[index]);
                        visited[i][j] = false;
                    }
                }
            }
            return new ArrayList<>(res);
        }

        private int m, n;
        private char[][] board;
        private boolean[][] visited;
        private int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        private TrieNode root = new TrieNode();
        private Set<String> res = new HashSet<>();

        private void backtrace(int x, int y, TrieNode p) {
            if (p.s != null) res.add(p.s);
            for (int[] dir : dirs) {
                int xx = x + dir[0], yy = y + dir[1];
                if (!inArea(xx, yy) || visited[xx][yy]) continue;
                int index = board[xx][yy] - 'a';
                if (p.children[index] != null) {
                    visited[xx][yy] = true;
                    backtrace(xx, yy, p.children[index]);
                    visited[xx][yy] = false;
                }
            }
        }

        private boolean inArea(int x, int y) {
            return 0 <= x && x < m && 0 <= y && y < n;
        }


        // 字典树(变体)
        class TrieNode {
            String s;
            TrieNode[] children = new TrieNode[26];
        }

        private void insert(String s) {
            TrieNode p = root;
            for (char c : s.toCharArray()) {
                int index = c - 'a';
                if (p.children[index] == null) {
                    p.children[index] = new TrieNode();
                }
                p = p.children[index];
            }
            p.s = s;
        }

    }

}
