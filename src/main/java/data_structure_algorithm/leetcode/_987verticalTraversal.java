package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _987verticalTraversal {

    public static class Solution1 {

        /**
         DFS + Sort: O(NlogN), O(N)
         Note: 先DFS求<col, row, val>的三元组数组，然后排序，最后构建返回结果。(也可以BFS + Sort)
         */
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            // DFS: 得到每个节点的<col, row, val>
            List<int[]> nodes = new ArrayList<int[]>();
            dfs(root, 0, 0, nodes);

            // 排序：优先按照col，col相同按照row，row相同按照val
            Collections.sort(nodes, new Comparator<int[]>() {
                public int compare(int[] tuple1, int[] tuple2) {
                    if (tuple1[0] != tuple2[0]) {
                        return tuple1[0] - tuple2[0];
                    } else if (tuple1[1] != tuple2[1]) {
                        return tuple1[1] - tuple2[1];
                    } else {
                        return tuple1[2] - tuple2[2];
                    }
                }
            });

            // 同一列的所有节点放入同一个数组中
            List<List<Integer>> ans = new ArrayList<List<Integer>>();
            int size = 0;
            int lastcol = Integer.MIN_VALUE;
            for (int[] tuple : nodes) {
                int col = tuple[0], row = tuple[1], value = tuple[2];
                if (col != lastcol) {
                    lastcol = col;
                    ans.add(new ArrayList<Integer>());
                    size++;
                }
                ans.get(size - 1).add(value);
            }
            return ans;
        }

        public void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
            if (node == null) {
                return;
            }
            nodes.add(new int[]{col, row, node.val});
            dfs(node.left, row + 1, col - 1, nodes);
            dfs(node.right, row + 1, col + 1, nodes);
        }

    }

}
