package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _25reverseKGroup {

    public static class Solution1 {

        /**
         分组交换: O(N), O(1)
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode(0, head), lastTail = dummy, cur = head, tail = findTail(head, k);
            while (tail != null) {
                lastTail.next = tail;
                lastTail = cur;

                ListNode nextHead = tail.next, prev = nextHead;
                while (cur != nextHead) {
                    ListNode next = cur.next;
                    cur.next = prev;
                    prev = cur;
                    cur = next;
                }
                tail = findTail(nextHead, k);
            }
            return dummy.next;
        }

        private ListNode findTail(ListNode head, int k) {
            for (int i = 1; i < k && head != null; i++) {
                head = head.next;
            }
            return head;
        }

    }

    public static class Solution2 {
        /**
         逐个交换: O(N), O(1) / 先定位要反转部分的前驱节点，然后固定prev和cur节点，将next节点不断交换到前面来
         */
         public ListNode reverseKGroup(ListNode head, int k) {
             ListNode dummy = new ListNode(0, head), prev = dummy, p = head;
             int count = 1;
             while (p != null) {
                 p = p.next;
                 if (++count == k && p != null) {
                     // reverse
                     head = prev.next;
                     for (int i = 1; i < k; i++) {
                         ListNode next = head.next;
                         head.next = next.next;
                         next.next = prev.next;
                         prev.next = next;
                     }
                     // update
                     prev = head;
                     p = prev.next;
                     count = 1;
                 }
             }
             return dummy.next;
         }

    }

}
