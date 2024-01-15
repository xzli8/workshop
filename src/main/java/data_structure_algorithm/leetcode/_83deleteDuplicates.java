package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _83deleteDuplicates {

    public static class Solution1 {

        /**
         要删除链表中的某个节点，需要定位到该节点的前驱节点
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode deleteDuplicates(ListNode head) {
            ListNode p = head;
            while (p != null && p.next != null) {
                if (p.next.val == p.val) p.next = p.next.next;
                else p = p.next;
            }
            return head;
        }

    }

}
