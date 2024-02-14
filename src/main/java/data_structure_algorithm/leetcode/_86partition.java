package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _86partition {

    public static class Solution1 {

        /**
         双指针：先分隔成两个链表，然后再合并
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode partition(ListNode head, int x) {
            ListNode dummy1 = new ListNode(), dummy2 = new ListNode();
            ListNode p1 = dummy1, p2 = dummy2, p = head;
            while (p != null) {
                ListNode next = p.next;
                if (p.val < x) {
                    p.next = p1.next;
                    p1.next = p;
                    p1 = p1.next;
                } else {
                    p.next = p2.next;
                    p2.next = p;
                    p2 = p2.next;
                }
                p = next;
            }
            p1.next = dummy2.next;
            dummy2.next = null;
            return dummy1.next;
        }

    }

}
