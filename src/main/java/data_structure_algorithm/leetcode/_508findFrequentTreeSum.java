package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _508findFrequentTreeSum {


    public static class Solution1 {

        /**
         DFS(PostOrder) + HashMap
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] findFrequentTreeSum(TreeNode root) {
            dfs(root);
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

        private Map<Integer, Integer> sum2Count = new HashMap<>();
        private int maxCount = 0;
        private List<Integer> res = new ArrayList<>();

        private int dfs(TreeNode cur) {
            if (cur == null) return 0;
            int sum = dfs(cur.left) + dfs(cur.right) + cur.val;
            int count = sum2Count.getOrDefault(sum, 0) + 1;
            if (count > maxCount) {
                maxCount = count;
                res.clear();
                res.add(sum);
            } else if (count == maxCount) {
                res.add(sum);
            }
            sum2Count.put(sum, count);
            return sum;
        }

    }



    public static class Solution2 {

        public int[] findFrequentTreeSum(TreeNode root) {
            sum(root);
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

        private Map<Integer, Integer> sum2Count = new HashMap<>();
        private int maxCount = 0;
        private List<Integer> res = new ArrayList<>();

        private int sum(TreeNode node) {
            int sum = node.val;
            if (node.left != null) sum += sum(node.left);
            if (node.right != null) sum += sum(node.right);
            int count = sum2Count.getOrDefault(sum, 0) + 1;
            if (count > maxCount) {
                maxCount = count;
                res.clear();
                res.add(sum);
            } else if (count == maxCount) {
                res.add(sum);
            }
            sum2Count.put(sum, count);
            return sum;
        }

    }

}
