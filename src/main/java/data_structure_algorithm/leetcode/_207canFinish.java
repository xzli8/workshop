package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _207canFinish {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(canFinish(20, new int[][] {{0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}}));
        }

        /**
         BFS拓扑排序
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构建邻接表与入度
            int[] indeg = new int[numCourses];
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < prerequisites.length; i++) {
                g.get(prerequisites[i][1]).add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 将入度为0的点全部入队
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并且删除该点和以该点为起点的边
            int visited = 0;
            while (!q.isEmpty()) {
                visited++;
                int u = q.poll();
                // 将终点的入度减1，若入度为0，入队
                for (int v : g.get(u)) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses;
        }

    }



    public static class Solution2 {

        @Test
        public void test() {
            System.out.println(canFinish(20, new int[][] {{0, 10}, {3, 18}, {5, 5}, {6, 11}, {11, 14}, {13, 1}, {15, 1}, {17, 4}}));
        }

        /**
         拓扑排序（邻接表）
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构建邻接表与入度
            int[] indeg = new int[numCourses];
            List<Integer>[] g = new List[numCourses];
            Arrays.fill(g, new ArrayList<>());
            for (int i = 0; i < prerequisites.length; i++) {
                g[prerequisites[i][1]].add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 将入度为0的点全部入队
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并且删除该点和以该点为起点的边
            int visited = 0;
            while (!q.isEmpty()) {
                visited++;
                int u = q.poll();
                // 将终点的入度减1，若入度为0，入队
                for (int v : g[u]) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses;
        }

    }

    @Test
    public void test() {
        int n = 3;
        List<Integer>[] lists = new List[n];
        Arrays.fill(lists, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            lists[i].add(i);
        }
        for (int i = 0; i < n; i++) {
            for (int num : lists[i]) {
                System.out.println(num);
            }
        }
    }

}
