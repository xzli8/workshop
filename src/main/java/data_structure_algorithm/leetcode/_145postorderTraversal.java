package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _145postorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> postorderTraversal(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(TreeNode root, List<Integer> res) {
             if (root == null) return;

             traversal(root.left, res);
             traversal(root.right, res);
             res.add(root.val);
         }

    }



    public static class Solution2 {

        /**
         迭代
         思路：前序"根左右"，后续"左右根"，在前序遍历过程中交换左右子节点的处理顺序，最后将结果反转
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                TreeNode cur = s.pop();
                res.add(cur.val);
                if (cur.left != null) s.push(cur.left);
                if (cur.right != null) s.push(cur.right);
            }
            Collections.reverse(res);
            return res;
        }

    }



    public static class Solution3 {

        /**
         Morris遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null) {
                if (cur.left != null) {
                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                    } else {
                        mostRight.right = null;
                        addRes(cur.left, res);
                        cur = cur.right;
                    }
                } else {
                    cur = cur.right;
                }
            }
            addRes(root, res);
            return res;
        }

        private void addRes(TreeNode node, List<Integer> res) {
            TreeNode head = reverse(node);
            TreeNode cur = head;
            while (cur != null) {
                res.add(cur.val);
                cur = cur.right;
            }
            reverse(head);
        }

        // 单链表反转(将right指针看作是next指针)
        private TreeNode reverse(TreeNode cur) {
            TreeNode prev = null;
            while (cur != null) {
                TreeNode next = cur.right;
                cur.right = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }


}
