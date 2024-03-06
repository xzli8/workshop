package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _95generateTrees {

    public static class Solution1 {

        /**
         回溯
         */
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new ArrayList<>();
            return dfs(1, n);
        }

        private List<TreeNode> dfs(int start, int end) {
            List<TreeNode> res = new ArrayList<>();
            if (start > end) {
                res.add(null);
                return res;
            }

            // 枚举根节点
            for (int i = start; i <= end; i++) {
                // 递归计算左右子树集合
                List<TreeNode> lefts = dfs(start, i - 1), rights = dfs(i + 1, end);

                // 分别从左右子树集合中选出一棵，拼接到根节点上
                for (TreeNode left : lefts) {
                    for (TreeNode right : rights) {
                        res.add(new TreeNode(i, left, right));
                    }
                }
            }
            return res;
        }

    }

}
