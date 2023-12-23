package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _107levelOrderBottom {

    public static class Solution1 {

        /**
         BFS：遍历完成后反转结果
         时间复杂度：O(N)
         空间复杂度：O(N
         */
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> tmp = new ArrayList<>(size);
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    tmp.add(cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                res.add(tmp);
            }
            Collections.reverse(res);
            return res;
        }


    }



    public static class Solution2 {

        /**
         BFS：每次向队头加
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<List<Integer>> levelOrderBottom(TreeNode root) {
             LinkedList<List<Integer>> res = new LinkedList<List<Integer>>();
             if (null == root) {
                 return res;
             }

             Queue<TreeNode> queue = new LinkedList<TreeNode>();
             queue.offer(root);
             while (!queue.isEmpty()) {
                 Integer size = queue.size();
                 List<Integer> list = new ArrayList<Integer>();
                 for (int i = 0; i < size; i++) {
                     TreeNode node = queue.poll();
                     list.add(node.val);
                     if (null != node.left) queue.offer(node.left);
                     if (null != node.right) queue.offer(node.right);
                 }
                 // 每次都往队头塞
                 res.addFirst(list);
             }
             return res;
         }

    }

}
