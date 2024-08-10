package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2095deleteMiddle {

    public static class Solution1 {

        /**
         Use two pointers(fast-slow) to locate the middle point, then remove it using it's previous
         TimeComplexity: O(N)
         SpaceComplexity: O(1)
         */
        public ListNode deleteMiddle(ListNode head) {
            ListNode dummy = new ListNode(0, head);
            ListNode fast = head, slow = head, prev = dummy;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = slow.next;
            return dummy.next;
        }

    }

}
