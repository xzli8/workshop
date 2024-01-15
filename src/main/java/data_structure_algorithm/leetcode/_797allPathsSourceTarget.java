package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _797allPathsSourceTarget {

    public static class Solution1 {

        /**
         DFS
         */
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 路径总是从0出发
            LinkedList<Integer> path = new LinkedList<>();
            path.add(0);

            // 开始遍历
            dfs(graph, 0, path);
            return paths;
        }

        private List<List<Integer>> paths = new ArrayList<>();

        private void dfs(int[][] graph, int i, LinkedList<Integer> path) {
            // 递归终止条件：到达终点
            if (i == graph.length - 1) {
                // 这里要copy一份path到最终结果中，否则因为传递的是引用的值，后面的修改会作用到path上
                paths.add(new LinkedList<>(path));
                return;
            }

            // 遍历相邻节点
            for (int adj : graph[i]) {
                // 遍历到的节点加入到路径中来
                path.add(adj);

                // 遍历下一层
                dfs(graph, adj, path);

                // 回溯，从路径中移除当前节点
                path.removeLast();
            }
        }

    }

}
