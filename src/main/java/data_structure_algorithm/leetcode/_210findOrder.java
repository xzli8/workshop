package data_structure_algorithm.leetcode;

import java.util.*;

public class _210findOrder {

    public static class Solution1 {

        /**
         BFS拓扑排序
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 构建邻接表和入度
            int[] indeg = new int[numCourses];
            List<List<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < prerequisites.length; i++) {
                g.get(prerequisites[i][1]).add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 入度为0的点全部加入队列
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并删除该点连接的边
            int[] res = new int[numCourses];
            int visited = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                res[visited++] = u;
                for (int v : g.get(u)) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses ? res : new int[0];
        }

    }



    public static class Solution2 {

        /**
         BFS拓扑排序
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 构建邻接表和入度
            int[] indeg = new int[numCourses];
            List<Integer>[] g = new List[numCourses];
            for (int i = 0; i < numCourses; i++) g[i] = new ArrayList<>();
            // Arrays.fill(g, new ArrayList<>());  // 这样填充的是同一个ArrayList对象，有问题
            for (int i = 0; i < prerequisites.length; i++) {
                g[prerequisites[i][1]].add(prerequisites[i][0]);
                indeg[prerequisites[i][0]]++;
            }

            // 入度为0的点全部加入队列
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indeg[i] == 0) {
                    q.offer(i);
                }
            }

            // 将入度为0的点加入结果集，并删除该点连接的边
            int[] res = new int[numCourses];
            int visited = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                res[visited++] = u;
                for (int v : g[u]) {
                    indeg[v]--;
                    if (indeg[v] == 0) {
                        q.offer(v);
                    }
                }
            }
            return visited == numCourses ? res : new int[0];
        }

    }

}
