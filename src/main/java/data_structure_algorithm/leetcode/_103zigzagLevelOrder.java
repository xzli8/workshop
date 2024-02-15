package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.*;

public class _103zigzagLevelOrder {

    public static class Solution0 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            boolean reverse = false;
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    level.add(cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                if (reverse) Collections.reverse(level);
                reverse = !reverse;
                res.add(level);
            }
            return res;
        }

    }



    public static class Solution1 {

        /**
         BFS：奇数层的层结果反转一下再加入总结果
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) return res;

            boolean reverse = false;
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                List<Integer> tmp = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    tmp.add(cur.val);
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                }
                if (reverse) Collections.reverse(tmp);
                res.add(tmp);
                reverse = !reverse;
            }
            return res;
        }

    }


}
