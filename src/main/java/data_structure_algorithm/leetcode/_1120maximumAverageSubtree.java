package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

public class _1120maximumAverageSubtree {

    /**
     * ref: https://www.cnblogs.com/cnoodle/p/14414330.html
     */

    public static class Solution1 {

        public double maximumAverageSubtree(TreeNode root) {
            countAndSum(root);
            return maxAverage;
        }

        private double maxAverage = Double.MIN_VALUE;
        private Pair<Integer, Integer> countAndSum(TreeNode node) {
            if (node == null) return new Pair<>(0, 0);
            Pair<Integer, Integer> leftPair = countAndSum(node.left), rightPair = countAndSum(node.right);
            int count = leftPair.getKey() + rightPair.getKey() + 1, sum = leftPair.getValue() + rightPair.getValue() + node.val;
            maxAverage = Math.max(maxAverage, sum / (double) count);
            return new Pair<>(count, sum);
        }

    }

}
