package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _105buildTree {

    public static class Solution0 {

        /**
         DFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int n = preorder.length;
            this.preorder = preorder;
            for (int i = 0; i < n; i++) inorderVal2Idx.put(inorder[i], i);
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



    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public TreeNode buildTree(int[] preorder, int[] inorder) {
             return buildTreeCore(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
         }

         private TreeNode buildTreeCore(int[] preorder, int[] inorder, int startPreorder, int endPreorder, int startInorder, int endInorder) {
             // 先序遍历结果的第一个就是根节点
             TreeNode root = new TreeNode(preorder[startPreorder]);

             // 在中序遍历结果中定位根节点位置，将中序遍历结果分为左右子树的中序遍历结果（这里要求树没有重复元素）
             int rootInorder = startInorder;
             while (rootInorder <= endInorder && inorder[rootInorder] != preorder[startPreorder]) {
                 rootInorder++;
             }

             // 分别构建左右子树
             int leftLen = rootInorder - startInorder;
             if (leftLen > 0) {
                 root.left = buildTreeCore(preorder, inorder, startPreorder + 1, startPreorder + leftLen, startInorder, rootInorder - 1);
             }
             int rightLen = endInorder - rootInorder;
             if (rightLen > 0) {
                 root.right = buildTreeCore(preorder, inorder, startPreorder + leftLen + 1, endPreorder, rootInorder + 1, endInorder);
             }
             return root;
         }

    }



    public static class Solution2 {

        /**
         DFS + map：用空间换时间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // 初始化中序遍历(value, index)映射
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTreeCore(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
        }

        private Map<Integer, Integer> map = new HashMap<>();
        private TreeNode buildTreeCore(int[] preorder, int startPreorder, int endPreorder,
                                       int startInorder, int endInorder) {
            TreeNode root = new TreeNode(preorder[startPreorder]);
            int rootInorder = map.get(preorder[startPreorder]);
            int leftLen = rootInorder - startInorder;
            if (leftLen > 0) {
                root.left = buildTreeCore(preorder, startPreorder + 1, startPreorder + leftLen, startInorder, rootInorder - 1);
            }
            int rightLen = endInorder - rootInorder;
            if (rightLen > 0) {
                root.right = buildTreeCore(preorder, startPreorder + leftLen + 1, endPreorder, rootInorder + 1, endInorder);
            }
            return root;
        }

    }

}
