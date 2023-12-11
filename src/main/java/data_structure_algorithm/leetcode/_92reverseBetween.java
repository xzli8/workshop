package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _92reverseBetween {

    public static class Solution1 {

        /**
         双指针：先定位要反转部分的前驱节点，然后逐个反转
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(0, head);

            // 定位待反转部分的前驱节点
            ListNode prev = dummy;
            for (int i = 0; i < left-1; i++) {
                prev = prev.next;
            }
            head = prev.next;

            // 逐个调整（prev和head的指向节点保持不变，不断将head.next指向的节点移到prev后面）
            for (int i = left; i < right; i++) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            return dummy.next;
        }


    }

}
