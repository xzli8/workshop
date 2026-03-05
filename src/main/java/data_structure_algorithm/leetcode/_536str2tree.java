package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _536str2tree {

    /**
     * ref: https://leetcode.doocs.org/lc/536/
     */

    public static class Solution1 {

        /**
         * DFS: O(N), O(N)
         */
        public TreeNode str2tree(String s) {
            if ("".equals(s)) {
                return null;
            }

            // 解析当前节点的val
            int p = s.indexOf("(");
            if (p == -1) {
                return new TreeNode(Integer.parseInt(s));
            }
            TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, p)));

            // 找左右子树
            int start = p;
            int cnt = 0;
            for (int i = p; i < s.length(); ++i) {
                if (s.charAt(i) == '(') {
                    ++cnt;
                } else if (s.charAt(i) == ')') {
                    --cnt;
                }
                if (cnt == 0) {
                    // 找到了左子树的范围
                    if (start == p) {
                        root.left = str2tree(s.substring(start + 1, i));
                        start = i + 1;
                    }
                    // 找到了右子树的范围
                    else {
                        root.right = str2tree(s.substring(start + 1, i));
                    }
                }
            }
            return root;
        }

    }

}
