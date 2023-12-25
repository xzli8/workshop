package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class _106buildTree {

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
