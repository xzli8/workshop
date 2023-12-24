package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _117connect {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }



    public class Solution1 {

        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public Node connect(Node root) {
             if (root == null) return root;

             Queue<Node> q = new ArrayDeque<>();
             q.offer(root);
             while (!q.isEmpty()) {
                 int size = q.size();
                 Node[] tmp = new Node[size];
                 for (int i = 0; i < size; i++) {
                     Node cur = q.poll();
                     if (cur.left != null) q.offer(cur.left);
                     if (cur.right != null) q.offer(cur.right);
                     tmp[i] = cur;
                 }
                 for (int i = 0; i < size - 1; i++) {
                     tmp[i].next = tmp[i + 1];
                 }
             }
             return root;
         }

    }



    public class Solution2 {


        /**
         BFS
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public Node connect(Node root) {
             if (root == null) return root;

             Queue<Node> q = new ArrayDeque<>();
             q.offer(root);
             while (!q.isEmpty()) {
                 int size = q.size();
                 Node prev = null;
                 for (int i = 0; i < size; i++) {
                     Node cur = q.poll();
                     if (cur.left != null) q.offer(cur.left);
                     if (cur.right != null) q.offer(cur.right);

                     if (i == 0) {
                         prev = cur;
                     } else {
                         prev.next = cur;
                         prev = prev.next;
                     }
                     prev.next = null;
                 }
             }
             return root;
         }

    }



    public class Solution3 {

        /**
         BFS：二叉树的每一层都是由next连起来的链表，可以不用队列存储
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public Node connect(Node root) {
            if (root == null) return root;

            Node head = root;   // 每一层的头节点
            while (head != null) {
                Node dummy = new Node(), tail = dummy;
                for (Node cur = head; cur != null; cur = cur.next) {
                    if (cur.left != null) tail = tail.next = cur.left;
                    if (cur.right != null) tail = tail.next = cur.right;
                }
                head = dummy.next;
            }
            return root;
        }

    }

}
