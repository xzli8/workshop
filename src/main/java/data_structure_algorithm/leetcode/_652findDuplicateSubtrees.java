package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _652findDuplicateSubtrees {

    public static class Solution1 {

        /**
         DFS + Hash: O(N), O(N)
         Note: DFS序列化时，前序/后序/层序都行，中序不行(中序没有根节点的确切信息，而前序根节点在第一位/后序根节点在最后一位/层序根节点在第一位)，但层序遍历这里不太好操作[无法找路径]
         */
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            dfs(root);
            return res;
        }

        private Map<String, Integer> str2Count = new HashMap<>();
        private List<TreeNode> res = new ArrayList<>();
        private String dfs(TreeNode root) {
            if (root == null) return "";
            String str = root.val + "," + dfs(root.left) + "," + dfs(root.right);
            // String str = dfs(root.left) + "," + dfs(root.right) + "," + root.val;
            // String str = dfs(root.left) + "," + root.val + "," + dfs(root.right);   // error case: [0,0,0,0,null,null,0,null,null,null,0], output: [[0],[0,null,0]], expected: [[0]]
            if (str2Count.merge(str, 1, Integer::sum) == 2) res.add(root);
            return str;
        }


    }

}
