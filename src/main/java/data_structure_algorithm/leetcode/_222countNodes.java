package data_structure_algorithm.leetcode;

public class _222countNodes {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }



    public static class Solution1 {

        /**
         普通二叉树 + 满二叉树
         时间复杂度：O(logN * logN)
         空间复杂度：O(logN) 树的高度
         */
        public int countNodes(TreeNode root) {
            if (root == null) return 0;
            int leftLevel = countLevel(root.left);
            int rightLevel = countLevel(root.right);
            if (leftLevel == rightLevel) {  // 左子树是满二叉树
                return (1 << leftLevel) + countNodes(root.right);
            } else {    // 左子树不是满二叉树
                return (1 << rightLevel) + countNodes(root.left);
            }
        }

        // 计算某个节点的层数
        private int countLevel(TreeNode root) {
            int level = 0;
            while (root != null) {
                level++;
                root = root.left;
            }
            return level;
        }


    }

}
