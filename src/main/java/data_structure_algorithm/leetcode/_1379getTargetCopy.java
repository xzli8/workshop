package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _1379getTargetCopy {

    public static class Solution1 {

        /**
         DFS/BFS + HashMap(Values must be different)
         TC:O(N)
         SC:O(N)
         */
         public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
             dfs(cloned);
             return val2Node.get(target.val);
         }

         private Map<Integer, TreeNode> val2Node = new HashMap<>();
         private void dfs(TreeNode root) {
             if (root == null) return;
             val2Node.put(root.val, root);
             dfs(root.left);
             dfs(root.right);
         }

    }


    public static class Solution2 {

        /**
         DFS/BFS: Search on both (Values could be different)
         TC: O(N)
         SC: O(N)
         */
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null) return null;
            if (original == target) return cloned;
            TreeNode left = getTargetCopy(original.left, cloned.left, target);
            if (left != null) return left;
            return getTargetCopy(original.right, cloned.right, target);
        }

    }

}
