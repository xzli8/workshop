package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _1669mergeInBetween {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
            ListNode dummy = new ListNode(0, list1);
            ListNode prev = dummy;
            for (int i = 0; i < a; i++) {
                prev = prev.next;
            }
            ListNode next = prev.next;
            for (int i = a; i <= b; i++) {
                next = next.next;
            }
            prev.next = list2;
            ListNode p = list2;
            while (p.next != null) {
                p = p.next;
            }
            p.next = next;
            return dummy.next;
        }

    }

}
