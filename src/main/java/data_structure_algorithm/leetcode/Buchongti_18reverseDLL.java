package data_structure_algorithm.leetcode;

public class Buchongti_18reverseDLL {

    /**
     * ref:https://www.geeksforgeeks.org/problems/reverse-a-doubly-linked-list/1
     */

    public static class Solution1 {

        class Node
        {
            int data;
            Node next, prev;
            Node(int data)
            {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }


        /**
         * 迭代：O(N), O(1)
         */
        public static Node reverseDLL(Node  head)
        {
            Node prev = null, cur = head;
            while (cur != null) {
                Node next = cur.next;
                cur.next = prev;
                cur.prev = next;
                prev = cur;
                cur = next;
            }
            return prev;
        }


        /**
         * 递归: O(N), O(N)
         */
        public Node reverse(Node head) {
            return reverse(null, head);
        }

        private Node reverse(Node prev, Node cur) {
            if (cur == null) {
                return prev;
            }
            Node next = cur.next;
            cur.prev = next;
            cur.next = prev;
            return reverse(cur, next);
        }

    }


}
