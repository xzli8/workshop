package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _1290getDecimalValue {

    public static class Solution1 {

        /**
         反转 + 遍历累加
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int getDecimalValue(ListNode head) {
            ListNode p = reverse(head);
            int sum = 0, radix = 1;
            while (p != null) {
                if (p.val == 1) sum += radix;
                radix = radix << 1;
                p = p.next;
            }
            return sum;
        }

        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

    }

}
