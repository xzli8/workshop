package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1008bstFromPreorder {

    public static class Solution1 {

        /**
         Sort + Build
         */
        public TreeNode bstFromPreorder(int[] preorder) {
            // sort to get inorder
            int n = preorder.length;
            int[] inorder = new int[n];
            for (int i = 0; i < n; i++) {
                inorder[i] = preorder[i];
            }
            Arrays.sort(inorder);

            // build from inorder and preorder
            this.preorder = preorder;
            for (int i = 0; i < n; i++) {
                inorderVal2Idx.put(inorder[i], i);
            }
            return build(0, n - 1, 0, n - 1);
        }

        private int[] preorder;
        private Map<Integer, Integer> inorderVal2Idx = new HashMap<>();
        private TreeNode build(int preorderLeft, int preorderRight, int inorderLeft, int inorderRight) {
            if (preorderLeft > preorderRight) return null;
            TreeNode root = new TreeNode(preorder[preorderLeft]);
            int inorderRoot = inorderVal2Idx.get(root.val), leftLen = inorderRoot - inorderLeft;
            root.left = build(preorderLeft + 1, preorderLeft + leftLen, inorderLeft, inorderRoot - 1);
            root.right = build(preorderLeft + leftLen + 1, preorderRight, inorderRoot + 1, inorderRight);
            return root;
        }


    }

}
