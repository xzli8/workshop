package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _106buildTree {

    public static class Solution0 {

        /**
         DFS + Hash: O(N), O(N)
         Note: 后序遍历的最后一个节点是根节点，因为没有重复元素，所以在中序遍历序列中可以找到一个唯一的值相同的元素，将中序遍历序列分为两部分，分别为左右子树。
         */
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            int n = inorder.length;
            this.postorder = postorder;
            for (int i = 0; i < n; i++) inorderVal2Idx.put(inorder[i], i);
            return dfs(0, n - 1, 0, n - 1);
        }

        private int[] postorder;
        private Map<Integer, Integer> inorderVal2Idx = new HashMap<>();

        private TreeNode dfs(int inorderStart, int inorderEnd, int postorderStart, int postorderEnd) {
            if (inorderStart > inorderEnd) return null;     // "if (postorderStart > postorderEnd) return null"也可以，任用其一
            TreeNode root = new TreeNode(postorder[postorderEnd]);
            int inorderRoot = inorderVal2Idx.get(root.val), leftLen = inorderRoot - inorderStart;
            root.left = dfs(inorderStart, inorderRoot - 1, postorderStart, postorderStart + leftLen - 1);
            root.right = dfs(inorderRoot + 1, inorderEnd, postorderStart + leftLen, postorderEnd - 1);
            return root;
        }

    }


    public static class Solution1 {

        /**
         DFS
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public TreeNode buildTree(int[] inorder, int[] postorder) {
             return buildTreeCore(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
         }

         private TreeNode buildTreeCore(int[] inorder, int[] postorder, int startInorder, int endInorder,
         int startPostorder, int endPostorder) {
             // 后序遍历的最后一个节点是根节点
             TreeNode root = new TreeNode(postorder[endPostorder]);

             // 定位中序遍历结果中根节点的位置
             int rootInorder = startInorder;
             while (rootInorder <= endInorder && inorder[rootInorder] != postorder[endPostorder]) {
                 rootInorder++;
             }

             // 构建左右子树
             int leftLen = rootInorder - startInorder;
             if (leftLen > 0) {
                 root.left = buildTreeCore(inorder, postorder, startInorder, rootInorder - 1, startPostorder, startPostorder + leftLen - 1);
             }
             int rightLen = endInorder - rootInorder;
             if (rightLen > 0) {
                 root.right = buildTreeCore(inorder, postorder, rootInorder + 1, endInorder, startPostorder + leftLen, endPostorder - 1);
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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            return buildTreeCore(postorder, 0, postorder.length - 1, 0, inorder.length - 1);
        }

        private Map<Integer, Integer> map = new HashMap<>();

        private TreeNode buildTreeCore(int[] postorder, int startPostorder, int endPostorder,
                                       int startInorder, int endInorder) {
            TreeNode root = new TreeNode(postorder[endPostorder]);
            int rootInorder = map.get(postorder[endPostorder]);
            int leftLen = rootInorder - startInorder;
            if (leftLen > 0) {
                root.left = buildTreeCore(postorder, startPostorder, startPostorder + leftLen - 1,
                        startInorder, rootInorder - 1);
            }
            int rightLen = endInorder - rootInorder;
            if (rightLen > 0) {
                root.right = buildTreeCore(postorder, startPostorder + leftLen, endPostorder - 1,
                        rootInorder + 1, endInorder);
            }
            return root;
        }

    }



}
