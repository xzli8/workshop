package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

import java.util.*;

public class _314verticalOrder {

    /**
     * Lintcode: https://www.lintcode.com/problem/651
     *
     * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
     * If two nodes are in the same row and column, the order should be from left to right.
     *
     * example1:
     *      input:
     *          [3,9,20,null,null,15,7]
     *      output:
     *          [
     *           [9],
     *           [3,15],
     *           [20],
     *           [7]
     *          ]
     *
     *  example2:
     *      input:
     *          [3,9,8,4,0,1,7]
     *      output:
     *          [
     *            [4],
     *            [9],
     *            [3,0,1],
     *            [8],
     *            [7]
     *          ]
     *
     *  example3:
     *      input:
     *          [3,9,8,4,0,1,7,null,null,null,2,5]
     *      output:
     *          [
     *            [4],
     *            [9,5],
     *            [3,0,1],
     *            [8,2],
     *            [7]
     *          ]
     *
     *  参考题解：https://www.cnblogs.com/grandyang/p/5278930.html
     */

    public static class Solution1 {

        /**
         BFS(level order): O(NlogN)[遍历O(N), TreeMap排序O(logN)], O(N)
         Note: 遍历的同时记录offset，将<offset, nodes>加入到TreeMap中，由TreeMap完成按照value(offset)的排序
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Map<Integer, List<Integer>> offset2Values = new TreeMap<>();
            Queue<Pair<Integer, TreeNode>> q = new ArrayDeque<>();  // k-offset, v-node
            q.offer(new Pair<>(0, root));
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Pair<Integer, TreeNode> pair = q.poll();
                    Integer offset = pair.getKey();
                    TreeNode node = pair.getValue();
                    offset2Values.computeIfAbsent(offset, o -> new ArrayList<>()).add(node.val);
                    if (node.left != null) q.offer(new Pair<>(offset - 1, node.left));
                    if (node.right != null) q.offer(new Pair<>(offset + 1, node.right));
                }
            }
            res.addAll(offset2Values.values());
            return res;
        }

    }



    public static class Solution2 {

        /**
         DFS: O(NloglogN), O(N)
         Note: DFS遍历二叉树，记录每个节点的值、深度，以及横向的偏移量。然后对所有节点按照横向偏移量从小到大排序，再按照深度从小到大排序，最后按照横向偏移量分组。
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            // 从根节点开始dfs
            dfs(root, 0, 0);

            // 按照offset和depth排序
            List<List<Integer>> res = new ArrayList<>();
            for (List<int[]> nodes : offset2Nodes.values()) {
                nodes.sort(Comparator.comparingInt(a -> a[0])); // offset相同按照depth排序
                List<Integer> values = new ArrayList<>();
                for (int[] node : nodes) values.add(node[1]);
                res.add(values);
            }
            return res;
        }

        // k-offset, v-List<[depth, val]> (DFS不像BFS能够天然按照depth排序，所以需要单独记录每个节点的depth，后面也要单独处理这部分的排序，所以更耗时)
        private TreeMap<Integer, List<int[]>> offset2Nodes = new TreeMap<>();
        private void dfs(TreeNode root, int depth, int offset) {
            if (root == null) return;
            offset2Nodes.computeIfAbsent(offset, o -> new ArrayList<>()).add(new int[] {depth, root.val});
            dfs(root.left, depth + 1, offset - 1);
            dfs(root.right, depth + 1, offset + 1);
        }

    }

}
