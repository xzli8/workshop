package data_structure_algorithm.leetcode;


public class _708insert {

    /**
     * ref: https://www.cnblogs.com/cnoodle/p/13723715.html
     */



    public static class Solution1 {

        class Node {

            int val;
            Node next;

            Node() {
            }

            Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        public Node insert(Node head, int insertVal) {
            // corner case
            if (head == null) {
                Node node = new Node();
                node.val = insertVal;
                node.next = node;
                return node;
            }

            // normal case
            // 找到最大节点max，之后一个节点就是最小节点min
            Node max = head;
            while (max.next != head && max.val <= max.next.val) {
                max = max.next;
            }
            Node min = max.next;
            Node cur = min;
            // 如果insertVal比max大或者比min小
            if (insertVal > max.val || insertVal <= min.val) {
                Node node = new Node();
                node.val = insertVal;
                node.next = min;
                max.next = node;
            } else {
                while (cur.next.val < insertVal) {
                    cur = cur.next;
                }
                Node node = new Node(insertVal, cur.next);
                cur.next = node;
            }
            return head;
        }
    }

}
