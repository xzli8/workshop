package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _589preorder {

    public static class Solution1 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public List<Integer> preorder(Node root) {
             List<Integer> res = new ArrayList<>();
             traversal(root, res);
             return res;
         }

         private void traversal(Node root, List<Integer> res) {
             if (root == null) return;

             res.add(root.val);
             for (Node child : root.children) {
                 traversal(child, res);
             }
         }

    }



    public static class Solution2 {

        /**
         迭代
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> preorder(Node root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) return res;

            Deque<Node> s = new ArrayDeque<>();
            s.push(root);
            while (!s.isEmpty()) {
                Node cur = s.pop();
                res.add(cur.val);
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    Node child = cur.children.get(i);
                    if (child != null) s.push(child);
                }
            }
            return res;
        }

    }



}
