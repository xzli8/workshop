package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import org.junit.Test;

public class _1474deleteNodes {

    /**
     * Given the head of a linked list and two integers m and n. Traverse the linked list and remove some nodes in the following way:
     *
     * Start with the head as the current node.
     * Keep the first m nodes starting with the current node.
     * Remove the next n nodes
     * Keep repeating steps 2 and 3 until you reach the end of the list.
     * Return the head of the modified list after removing the mentioned nodes.
     *
     * Follow-up question: How can you solve this problem by modifying the list in-place?
     *
     * ref: https://www.cnblogs.com/cnoodle/p/14771306.html
     */

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(deleteNodes(ListNode.buildWithArray(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13}), 2, 3));
            System.out.println(deleteNodes(ListNode.buildWithArray(new int[] {1,2,3,4,5,6,7,8,9,10,11}), 1, 3));
        }

        public ListNode deleteNodes(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(0, head), prev = dummy;
            while (prev.next != null) {
                for (int i = 0; i < m && prev.next != null; i++) {
                    prev = prev.next;
                }
                ListNode cur = prev.next;
                for (int i = 0; i < n && cur != null; i++) {
                    cur = cur.next;
                }
                prev.next = cur;
            }
            return dummy.next;
        }

//        public ListNode deleteNodes(ListNode head, int m, int n) {
//            ListNode dummy = new ListNode(0, head), prev = dummy, cur = head;
//            while (cur != null) {
//                for (int i = 0; i < m && cur != null; i++) {
//                    prev = cur;
//                    cur = cur.next;
//                }
//                for (int i = 0; i < n && cur != null; i++) {
//                    cur = cur.next;
//                }
//                prev.next = cur;
//            }
//            return dummy.next;
//        }

    }

}
