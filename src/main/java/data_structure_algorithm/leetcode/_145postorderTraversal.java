package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _145postorderTraversal {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(logN)
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
         空间复杂度：O(logN)
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


}
