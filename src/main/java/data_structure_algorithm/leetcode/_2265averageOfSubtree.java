package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;
import javafx.util.Pair;

public class _2265averageOfSubtree {

    public static class Solution1 {

        /**
         DFS(postorder)
         */
        public int averageOfSubtree(TreeNode root) {
            countAndSum(root);
            return res;
        }

        private Integer res = 0;
        private Pair<Integer, Integer> countAndSum(TreeNode root) {
            if (root == null) return new Pair<>(0, 0);
            Pair<Integer, Integer> leftPair = countAndSum(root.left);
            Pair<Integer, Integer> rightPair = countAndSum(root.right);
            int count = leftPair.getKey() + rightPair.getKey() + 1;
            int sum = leftPair.getValue() + rightPair.getValue() + root.val;
            if (sum / count == root.val) res++;
            return new Pair<>(count, sum);
        }

    }

}
