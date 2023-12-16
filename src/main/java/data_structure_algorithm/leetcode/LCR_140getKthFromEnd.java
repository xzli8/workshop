package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class LCR_140getKthFromEnd {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode getKthFromEnd(ListNode head, int k) {
            if (null == head) return null;

            ListNode p1 = head;
            for (int i = 1; i < k; i++) {
                p1 = p1.next;
            }

            ListNode p2 = head;
            while (p1.next != null) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p2;
        }

    }

}
