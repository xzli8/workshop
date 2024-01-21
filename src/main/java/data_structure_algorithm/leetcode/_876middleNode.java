package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _876middleNode {

    public static class Solution1 {

        /**
         双指针：快慢指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode middleNode(ListNode head) {
            ListNode fast = head, slow = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

    }

}
