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

}
