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
         LevelOrder
         */
        public List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Map<Integer, List<Integer>> idx2Vals = new TreeMap<>();
            Queue<Pair<Integer, TreeNode>> q = new ArrayDeque<>();
            q.offer(new Pair<>(0, root));
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Pair<Integer, TreeNode> pair = q.poll();
                    Integer idx = pair.getKey();
                    TreeNode node = pair.getValue();
                    if (node.left != null) q.offer(new Pair<>(idx - 1, node.left));
                    if (node.right != null) q.offer(new Pair<>(idx + 1, node.right));
                    List<Integer> vals = idx2Vals.getOrDefault(idx, new ArrayList<>());
                    vals.add(node.val);
                    idx2Vals.put(idx, vals);
                }
            }
            res.addAll(idx2Vals.values());
            return res;
        }

    }

}
