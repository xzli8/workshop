package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _94inorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> inorderTraversal(TreeNode root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(TreeNode root, List<Integer> res) {
             if (root == null) return;
             traversal(root.left, res);
             res.add(root.val);
             traversal(root.right, res);
         }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<TreeNode> s = new ArrayDeque<>();
            TreeNode cur = root;
            while (cur != null || !s.isEmpty()) {
                if (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                } else {
                    cur = s.pop();
                    res.add(cur.val);
                    cur = cur.right;
                }
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode cur = root;
            while (cur != null) {
                // 有左子树时，找到左子树的最右节点
                if (cur.left != null) {
                    // 查找左子树的最右节点
                    TreeNode mostRight = cur.left;
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }

                    // 上面的循环停下来，要么mostRight.right == null, 要么mostRight.right == cur
                    // 当mostRight.right == right时，说明是第一次到达到该最右节点，需要将mostRight.right指向cur
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        cur = cur.left;
                    }
                    // 第二次到达该最右节点，将mostRight.right指向null
                    else {
                        mostRight.right = null;
                        res.add(cur.val);
                        cur = cur.right;
                    }
                }
                // 没有左子树时，考虑右子树
                else {
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
            return res;
        }

    }

}
