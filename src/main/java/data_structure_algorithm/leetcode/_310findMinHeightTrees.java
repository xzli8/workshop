package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _310findMinHeightTrees {

    public static class Solution1 {

        /**
         * 一般思路：挨个节点遍历bfs，统计下每个节点的高度，然后用map存储起来，后面查询这个高度的集合里最小的就可以了（TLE）
         */

        /**
         BFS拓扑排序：O(N), O(N)
         Note: 越靠近里面的节点越有可能是最小高度树，所以可以倒着来，从边缘开始，找到所有出度为1的节点，加入队列进行BFS，
            就可以找到从两边同时开始向中间靠近的节点，相当于将整个距离二分了。
         Ref: https://leetcode.cn/problems/minimum-height-trees/solutions/242910/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/
         */
        public List<Integer> findMinHeightTrees(int n, int[][] edges) {
            // 边界情况
            List<Integer> res = new ArrayList<>();
            if (n == 1) {
                res.add(0);
                return res;
            }

            // 构建邻接表和出度数组(这里是无向图，也可以看成双向有向图)
            List<Integer>[] g = new List[n];
            for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
            int[] deg = new int[n];
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
                deg[edge[0]]++;
                deg[edge[1]]++;
            }

            // BFS
            Queue<Integer> q = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                if (deg[i] == 1) q.offer(i);
            }
            while (!q.isEmpty()) {
                res = new ArrayList<>();
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int cur = q.poll();
                    res.add(cur);
                    for (int neighbor : g[cur]) {
                        if (--deg[neighbor] == 1) q.offer(neighbor);
                    }
                }
            }
            return res;
        }

    }

}
