package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

public class _998insertIntoMaxTree {

    public static class Solution1 {

        /**
         迭代: O(N), O(1)
         Note: 遍历寻找右子节点(有点类似二分查找和在链表中查找)
         ref: https://leetcode.cn/problems/maximum-binary-tree-ii/solutions/1785544/by-ac_oier-v82s/
         */
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            TreeNode node = new TreeNode(val), prev = null, cur = root;
            while (cur != null && cur.val > val) {
                prev = cur;
                cur = cur.right;    // 因为是在结尾附加val，所以在右子树
            }
            if (prev == null) {
                node.left = cur;
                return node;
            } else {
                prev.right = node;
                node.left = cur;
                return root;
            }
        }

    }


    public static class Solution2 {

        /**
         递归: O(N), O(N)
         */
        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            if(root == null) return new TreeNode(val);
            if(val > root.val){
                return new TreeNode(val, root, null);
            }
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }

    }

}
