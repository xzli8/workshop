package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _19removeNthFromEnd {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode sentinel = new ListNode();
            sentinel.next = head;

            ListNode prev = sentinel;
            ListNode slow = head;
            ListNode fast = head;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }

            while (fast != null) {
                fast = fast.next;
                prev = slow;
                slow = slow.next;
            }
            prev.next = slow.next;
            return sentinel.next;
        }

    }

}
