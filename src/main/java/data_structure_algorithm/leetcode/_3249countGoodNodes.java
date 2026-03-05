package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _3249countGoodNodes {

    public static class Solution1 {

        /**
         dfs(postorder): O(N), O(N)
         Note: 这里给的不是二叉树，而是一个一般树(图)，通常会先根据边来构建邻接表(或邻接矩阵)，然后遍历求解。
         ref: https://leetcode.cn/problems/count-the-number-of-good-nodes/solutions/2977809/tong-ji-hao-jie-dian-de-shu-mu-by-leetco-4q70/
         */
        public int countGoodNodes(int[][] edges) {
            // 根据边数组构建邻接表(parentId -> childIds)
            int n = edges.length + 1;   // 树中边的数量为节点数量减1，节点数量 = 边数 + 1
            g = new List[n];
            for (int i = 0; i < n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }

            // 从编号为0的节点(根节点)开始搜索
            dfs(0, -1);
            return res;
        }

        private int res = 0;
        private List<Integer>[] g;

        // 输入为当前遍历的节点 node 和其父节点 parent，返回值为以 node 为根节点的树的节点数量
        private int dfs(int node, int parent) {
            boolean valid = true;
            int size = 0, preChildSize = -1;    // 前一个节点的子节点数量(初始化为-1)
            for (int child : g[node]) {
                if (child == parent) continue;  // 因为邻接表g中存的是双向邻接关系，所以需要跳过节点parent
                int childSize = dfs(child, node);
                if (preChildSize == -1) {
                    preChildSize = childSize;
                }
                // 当前节点的子节点数量和前一个节点的子节点数量不同时，不是好节点
                else if (childSize != preChildSize) {
                    valid = false;
                }
                size += childSize;
            }
            if (valid) res++;
            return size + 1;
        }

    }

}
