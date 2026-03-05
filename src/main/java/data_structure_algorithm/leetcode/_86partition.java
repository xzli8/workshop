package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _86partition {

    public static class Solution1 {

        /**
         哑元节点+前驱节点：O(N), O(1)
         */
        public ListNode partition(ListNode head, int x) {
            if (head == null || head.next == null) return head;
            ListNode less = new ListNode(), greater = new ListNode(), p1 = less, p2 = greater, p = head;
            while (p != null) {
                if (p.val < x) {
                    p1.next = p;
                    p1 = p;
                    p = p.next;
                } else {
                    p2.next = p;
                    p2 = p;
                    p = p.next;
                }
            }
            p1.next = greater.next;
            p2.next = null;
            return less.next;
        }

    }

}
