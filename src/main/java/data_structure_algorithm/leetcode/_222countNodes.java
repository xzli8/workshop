package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _222countNodes {

    public class Solution1 {

        /**
         DFS：普通二叉树也可以这样
         时间复杂度：O(N)
         空间复杂度：O(logN)
         */
         public int countNodes(TreeNode root) {
             if (root == null) return 0;
             return countNodes(root.left) + countNodes(root.right) + 1;
         }

    }



    public class Solution2 {

         /**
             BFS：普通二叉树也可以这样
          */
         public int countNodes(TreeNode root) {
             return 0;
         }

    }



    public class Solution3 {

        /**
         完全二叉树 = 左子树是满二叉树 + 右子树是完全二叉树
         时间复杂度：O(logN * logN)
         空间复杂度：O(logN)
         */
        public int countNodes(TreeNode root) {
            if (root == null) return 0;

            // 计算左右子树深度
            int leftDepth = 0, rightDepth = 0;
            TreeNode left = root.left, right = root.right;
            while (left != null) {
                left = left.left;
                leftDepth++;
            }
            while (right != null) {
                right = right.right;
                rightDepth++;
            }

            // 左右子树深度相等，是满二叉树，计算满二叉树的节点个数
            if (leftDepth == rightDepth) {
                return (2 << leftDepth) - 1;
            }

            // 左右子树深度不相等，分别计算左右子树的节点数(左右子树两个分支只有一个能走下去)
            return countNodes(root.left) + countNodes(root.right) + 1;
        }

    }

}
