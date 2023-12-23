package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class _199rightSideView {

    public static class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left != null) q.offer(cur.left);
                    if (cur.right != null) q.offer(cur.right);
                    if (i == size - 1) res.add(cur.val);
                }
            }
            return res;
        }


    }

}
