package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _206reverseList {

    public static class Solution1 {

        /**
         * 迭代
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)
         */
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (null != head) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

    }



    public static class Solution2 {

        /**
         递归
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public ListNode reverseList(ListNode head) {
            return reverse(null, head);
        }

        public ListNode reverse(ListNode prev, ListNode node) {
            if (null == node) return prev;
            ListNode next = node.next;
            node.next = prev;
            return reverse(node, next);
        }

    }


}
