package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _652findDuplicateSubtrees {

    public static class Solution1 {

        /**
         DFS序列化：前序/后序都可以，中序不行
         考点：如何将一棵树唯一的序列化(层序/前序/后序)
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            dfs(root);
            return res;
        }

        private Map<String, Integer> str2Count = new HashMap<>();
        private List<TreeNode> res = new ArrayList<>();

        private String dfs(TreeNode cur) {
            if (cur == null) return "";
            String str = new StringBuilder().append(cur.val).append(",")
                    .append(dfs(cur.left)).append(",").append(dfs(cur.right)).toString();
            str2Count.put(str, str2Count.getOrDefault(str, 0) + 1);
            if (str2Count.get(str) == 2) res.add(cur);
            return str;
        }


    }

}
