package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _328oddEvenList {

    public static class Solution1 {

        /**
         先拆分再合并
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode oddEvenList(ListNode head) {
            ListNode odd = new ListNode(), even = new ListNode();
            ListNode p = head, p1 = odd, p2 = even;
            while (p != null && p.next != null) {
                p1.next = p;
                p1 = p1.next;
                p2.next = p.next;
                p2 = p2.next;
                p = p.next.next;
            }
            if (p != null) {
                p1.next = p;
                p1 = p1.next;
            }
            p2.next = null;
            p1.next = even.next;
            return odd.next;
        }

    }

}
