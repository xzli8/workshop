package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class _2101maximumDetonation {

    public static class Solution1 {

        @Test
        public void test() {
            int[][] bombs = new int[][] {{1,2,3}, {2,3,1}, {3,4,2}, {4,5,3}, {5,6,4}};
            System.out.println(maximumDetonation(bombs));
        }

        /**
         BFS
         */
        public int maximumDetonation(int[][] bombs) {
            int n = bombs.length, max = Integer.MIN_VALUE;
            boolean[] visited = new boolean[n];

            for (int i = 0; i < n; i++) {
                Arrays.fill(visited, false);
                Queue<int[]> q = new ArrayDeque<>();

                int count = 0;
                q.offer(bombs[i]);
                visited[i] = true;
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    count++;
                    for (int j = 0; j < n; j++) {
                        if (visited[j]) continue;
                        if (lieIn(cur, bombs[j])) {
                            visited[j] = true;
                            q.offer(bombs[j]);
                        }
                    }
                }
                max = Math.max(max, count);
            }
            return max;
        }

        private boolean lieIn(int[] src, int[] recv) {
            double dx = src[0] - recv[0], dy = src[1] - recv[1];
            return Math.sqrt(dx * dx + dy * dy) <= src[2];
        }

    }



    public static class Solution2 {

        /**
         DFS
         */
        public int maximumDetonation(int[][] bombs) {
            // init
            this.n = bombs.length;
            this.bombs = bombs;
            this.visited = new boolean[n];

            // loop
            for (int i = 0; i < n; i++) {
                // init
                count = 0;
                Arrays.fill(visited, false);

                // dfs
                visited[i] = true;
                dfs(bombs[i]);
                max = Math.max(max, count);
            }
            return max;
        }

        private int n, max = Integer.MIN_VALUE, count;
        private int[][] bombs;
        private boolean[] visited;

        private void dfs(int[] cur) {
            count++;
            for (int i = 0; i < n; i++) {
                if (visited[i]) continue;
                if (lieIn(cur, bombs[i])) {
                    visited[i] = true;
                    dfs(bombs[i]);
                }
            }
        }

        private boolean lieIn(int[] src, int[] recv) {
            double dx = src[0] - recv[0], dy = src[1] - recv[1];
            return Math.sqrt(dx * dx + dy * dy) <= src[2];
        }

    }

}
