package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _19removeNthFromEnd {

    public static class Solution1 {

        /**
         哑元节点 + 前驱节点 + 双指针：O(N), O(1)
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head), fast = dummy, slow = dummy;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }
            while (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }
            slow.next = slow.next.next;
            return dummy.next;
        }

    }

}
