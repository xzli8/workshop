package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _450deleteNode {

    public static class Solution1 {

        /**
         递归：通过交换指针而不是赋值完成，推荐使用
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public TreeNode deleteNode(TreeNode root, int key) {
             if (root == null) return null;
             if (root.val == key) {
                 if (root.left == null) return root.right;
                 if (root.right == null) return root.left;
                 // 找右子树的最小节点或者左子树的最大节点
                 TreeNode cur = root.right;
                 while(cur.left != null) {
                     cur = cur.left;
                 }
                 // 将root的左子树拼接到右子树最小节点的left上
                 cur.left = root.left;
                 return root.right;
             }
             if (root.val > key) root.left = deleteNode(root.left, key);
             if (root.val < key) root.right = deleteNode(root.right, key);
             return root;
         }

    }



    public static class Solution2 {

        /**
         迭代：通过交换指针而不是赋值完成，推荐使用
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public TreeNode deleteNode(TreeNode root, int key) {
            // 寻找待删除节点及其父节点
            TreeNode parent = null, cur = root;
            while (cur != null && cur.val != key) {
                parent = cur;
                if (cur.val > key) cur = cur.left;
                else cur = cur.right;
            }
            // 只有头节点
            if (parent == null) return deleteNode(cur);
            if (parent.left != null && parent.left.val == cur.val) parent.left = deleteNode(cur);
            if (parent.right != null && parent.right.val == cur.val) parent.right = deleteNode(cur);
            return root;
        }

        private TreeNode deleteNode(TreeNode target) {
            if (target == null) return target;
            if (target.left == null) return target.right;
            if (target.right == null) return target.left;
            // 找右子树的最小值或者左子树的最大值
            TreeNode cur = target.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            // 将当前节点的左子树放在右子树最小值的left上
            cur.left = target.left;
            return target.right;
        }

    }



    public static class Solution3 {

        /**
         迭代：通过赋值而不是交换指针完成，不推荐使用
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public TreeNode deleteNode(TreeNode root, int key) {
             // 寻找待删除的节点（及其父节点，因为要删除一个节点需要知道其父节点）
             TreeNode parent = null;
             TreeNode node = root;
             while (node != null && node.val != key) {
                 parent = node;
                 if (key < node.val) node = node.left;
                 else node = node.right;
             }
             if (node == null) return root;  // 未找到要删除的节点

             // 待删除节点有左右两个叶子节点
             if (node.left != null && node.right != null) {
                 // 查找左子树的最大节点（或右子树的最小节点）
                 TreeNode leftMaxNodeParent = node;
                 TreeNode leftMaxNode = node.left;
                 while (leftMaxNode.right != null) {
                     leftMaxNodeParent = leftMaxNode;
                     leftMaxNode = leftMaxNode.right;
                 }

                 // 将左子树最大节点的值赋给待删除节点（然后删除左子树最大节点）
                 // 这里采用赋值的方式不太好，因为实际情况中val可能不是一个简单的int，如果是大规模的数据复制，非常消耗资源
                 // 所以更好的做法是通过调整指针来删除节点，而不是赋值
                 node.val = leftMaxNode.val;

                 // 后面就是删除node了
                 node = leftMaxNode;
                 parent = leftMaxNodeParent;
             }

             // 待删除节点有一个叶子节点或者没有叶子节点，将待删除节点的父节点指向待删除节点的子节点
             TreeNode child;
             if (node.left != null) child = node.left;
             else if (node.right != null) child = node.right;
             else child = null;

             if (parent == null) root = child; // 根节点为待删除节点
             else if (parent.left == node) parent.left = child;
             else parent.right = child;

             return root;
         }

    }


}
