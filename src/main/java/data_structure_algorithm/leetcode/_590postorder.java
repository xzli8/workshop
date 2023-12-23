package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.Node;

import java.util.*;

public class _590postorder {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> postorder(Node root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(Node root, List<Integer> res) {
             if (root == null) return;

             for (Node child : root.children) {
                 traversal(child, res);
             }
             res.add(root.val);
         }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> postorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<Node> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                Node cur = s.pop();
                res.add(cur.val);
                for (Node child : cur.children) {
                    if (child != null) s.push(child);
                }
            }
            Collections.reverse(res);
            return res;
        }


    }



}
