package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _776splitBST {

    /**
     * Ref: https://leetcode.doocs.org/lc/776/
     */

    public static class Solution1 {

        /**
         * 递归(DFS-postorder): O(N), O(N)
         */
        public TreeNode[] splitBST(TreeNode root, int target) {
            if (root == null) return new TreeNode[] {null, null};

            // 当root.val <= target时，root的左子树都比target小，不需要split，去split右子树即可
            if (root.val <= target) {
                TreeNode[] res = splitBST(root.right, target);
                // root.right split成两部分: res[0] <= target, res[1] > target，将root.right指向res[0]，然后将res[0]赋值为root
                root.right = res[0];
                res[0] = root;
                return res;
            } else {
                TreeNode[] res = splitBST(root.left, target);
                root.left = res[1];
                res[1] = root;
                return res;
            }
        }

    }

}
