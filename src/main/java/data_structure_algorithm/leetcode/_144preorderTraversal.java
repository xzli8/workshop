package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _144preorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> preorderTraversal(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(TreeNode root, List<Integer> res) {
             if (root == null) return;

             res.add(root.val);
             traversal(root.left, res);
             traversal(root.right, res);
         }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                TreeNode cur = s.pop();
                res.add(cur.val);
                if (cur.right != null) s.push(cur.right);
                if (cur.left != null) s.push(cur.left);
            }
            return res;
        }

    }



    public static class Solution3 {

        /**
         Morris遍历
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<Integer> preorderTraversal(TreeNode root) {
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
                        res.add(cur.val);
                        cur = cur.left;
                    } else {
                        mostRight.right = null;
                        cur = cur.right;
                    }
                } else {
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
            return res;
        }

    }

}
