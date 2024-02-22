package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _889constructFromPrePost {

    public static class Solution0 {

        /**
         DFS + HashMap
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            this.preorder = preorder;
            int n = preorder.length;
            for (int i = 0; i < n; i++) postorderVal2Idx.put(postorder[i], i);
            return dfs(0, n - 1, 0, n - 1);
        }

        private int[] preorder;
        private Map<Integer, Integer> postorderVal2Idx = new HashMap<>();
        private TreeNode dfs(int preorderLeft, int preorderRight, int postorderLeft, int postorderRight) {
            TreeNode root = new TreeNode(preorder[preorderLeft]);
            if (preorderLeft == preorderRight) return root;
            int postorderIdx = postorderVal2Idx.get(preorder[preorderLeft + 1]);
            int leftLen = postorderIdx - postorderLeft + 1;
            if (leftLen > 0) root.left = dfs(preorderLeft + 1, preorderLeft + leftLen, postorderLeft, postorderIdx);
            int rightLen = postorderRight - postorderIdx - 1;
            if (rightLen > 0) root.right = dfs(preorderRight - rightLen + 1, preorderRight, postorderIdx + 1, postorderRight - 1);
            return root;
        }

    }



    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
             int n = preorder.length;
             return build(preorder, 0, n-1, postorder, 0, n-1);
         }

         public TreeNode build(int[] preorder, int preStart, int preEnd,
                             int[] postorder, int postStart, int postEnd) {
             // 构建当前节点
             TreeNode node = new TreeNode(preorder[preStart]);
             if (preStart == preEnd) {
                 return node;
             }

             // 在后续遍历序列中找当前节点的后一个节点(也就是左子树的根节点),将后续遍历序列分为左右两部分，分别是当前节点的左右子树
             int postLeftRoot = postStart;
             while (postLeftRoot <= postEnd && postorder[postLeftRoot] != preorder[preStart + 1]) {
                 postLeftRoot++;
             }

             // 当左子树长度大于0时，构建左子树
             int leftLen = postLeftRoot - postStart + 1;
             if (leftLen > 0) {
                 node.left = build(preorder, preStart + 1, preStart + leftLen,
                 postorder, postStart, postLeftRoot);
             }

             // 当右子树长度大于0时，构建右子树
             int rightLen = postEnd - postLeftRoot - 1;
             if (rightLen > 0) {
                 node.right = build(preorder, preEnd - rightLen + 1, preEnd,
                 postorder, postLeftRoot + 1, postEnd - 1);
             }
             return node;
         }

    }



    public static class Solution2 {

        /**
         DFS + map：用空间换时间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            int n = preorder.length;
            Map<Integer, Integer> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                map.put(postorder[i], i);
            }

            return build(preorder, 0, n-1, map, 0, n-1);
        }

        public TreeNode build(int[] preorder, int preStart, int preEnd,
                              Map<Integer, Integer> map, int postStart, int postEnd) {
            // 构建当前节点
            TreeNode node = new TreeNode(preorder[preStart]);
            if (preStart == preEnd) {
                return node;
            }

            // 在后续遍历序列中找当前节点的后一个节点(也就是左子树的根节点),将后续遍历序列分为左右两部分，分别是当前节点的左右子树
            int postLeftRoot = map.get(preorder[preStart + 1]);

            // 当左子树长度大于0时，构建左子树
            int leftLen = postLeftRoot - postStart + 1;
            if (leftLen > 0) {
                node.left = build(preorder, preStart + 1, preStart + leftLen, map, postStart, postLeftRoot);
            }

            // 当右子树长度大于0时，构建右子树
            int rightLen = postEnd - postLeftRoot - 1;
            if (rightLen > 0) {
                node.right = build(preorder, preEnd - rightLen + 1, preEnd, map, postLeftRoot + 1, postEnd - 1);
            }
            return node;
        }

    }

}
