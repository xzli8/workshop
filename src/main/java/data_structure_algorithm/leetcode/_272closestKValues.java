package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _272closestKValues {

    /**
     * Ref: https://leetcode.doocs.org/lc/272/
     */

    public static class Solution1 {

        /**
         * DFS(postorder): O(N), O(N)
         */
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            dfs(root, target, k);
            return res;
        }

        private List<Integer> res = new ArrayList<>();
        private void dfs(TreeNode root, double target, int k) {
            if (root == null) return;
            dfs(root.left, target, k);
            if (res.size() < k) {
                res.add(root.val);
            } else {
                if (Math.abs(root.val - target) >= Math.abs(res.get(0) - target)) return;
                res.remove(0);
                res.add(root.val);
            }
            dfs(root.right, target, k);
        }


    }

}
