package data_structure_algorithm.leetcode;

import java.util.*;

public class _207canFinish {

    public static class Solution1 {

        /**
         BFS拓扑排序
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构建邻接表与入度
            int[] indeg = new int[numCourses];
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) g.add(new ArrayList<>());
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

        /**
         拓扑排序（邻接表）
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 构建邻接表与入度
            int[] indeg = new int[numCourses];
            List<Integer>[] g = new List[numCourses];
            for (int i = 0; i < numCourses; i++) g[i] = new ArrayList<>();
            // Arrays.fill(g, new ArrayList<>());    // 这样写填充的是同一个ArrayList对象，有问题
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

}
