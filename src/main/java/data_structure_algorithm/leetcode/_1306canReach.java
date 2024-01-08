package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class _1306canReach {


    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canReach(int[] arr, int start) {
            // 初始化
            this.n = arr.length; this.arr = arr;
            this.visited = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (arr[i] == 0) this.ends.add(i);
            }

            // 遍历
            return dfs(start);
        }

        private int n;
        private int[] arr;
        private boolean[] visited;
        private Set<Integer> ends = new HashSet<>();
        private int[] dirs = new int[] {1, -1};

        private boolean dfs(int cur) {
            if (ends.contains(cur)) return true;
            for (int dir : dirs) {
                int x = cur + dir * arr[cur];
                // 边界和visited的判断，放在dfs开头作为递归结束条件也可以，放在这里作为进入下一层递归的条件也可以
                if (0 <= x && x < n && !visited[x]) {
                    visited[x] = true;
                    if (dfs(x)) return true;
                }
            }
            return false;
        }


    }



    public static class Solution2 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canReach(int[] arr, int start) {
            // 初始化
            int[] dirs = new int[] {1, -1};
            int n = arr.length;
            boolean[] visited = new boolean[n];
            Set<Integer> ends = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (arr[i] == 0) ends.add(i);
            }
            Queue<Integer> q = new ArrayDeque<>();
            visited[start] = true;
            q.offer(start);

            // 遍历
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (ends.contains(cur)) return true;
                for (int dir : dirs) {
                    int x = cur + dir * arr[cur];
                    if (0 <= x && x < n && !visited[x]) {
                        visited[x] = true;
                        q.offer(x);
                    }
                }
            }
            return false;
        }


    }

}
