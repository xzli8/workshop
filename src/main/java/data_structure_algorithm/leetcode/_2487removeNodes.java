package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2487removeNodes {

    public static class Solution1 {

        /**
         Reverse
         */
        public ListNode removeNodes(ListNode head) {
            // reverse list
            head = reverse(head);
            ListNode cur = head;
            while (cur.next != null) {
                if (cur.val > cur.next.val) {
                    cur.next = cur.next.next;
                } else {
                    cur = cur.next;
                }
            }
            return reverse(head);
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }

}
