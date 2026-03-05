package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _61rotateRight {

    public static class Solution0 {

        /**
         计数+求余找split point：O(N), O(1)
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) return head;

            ListNode p = head;
            int n = 0;
            while (p != null) {
                p = p.next;
                n++;
            }
            if (k % n == 0) return head;    // 注意处理特殊情况

            // 找到分割点(倒数第k%n个节点)
            ListNode slow = head, fast = head;
            for (int i = 0; i < k % n; i++) {
                fast = fast.next;
            }
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            ListNode newHead = slow.next;
            slow.next = null;
            fast.next = head;
            return newHead;
        }

    }

    public static class Solution1 {

        /**
         将链表闭合成环后再在合适的位置断开
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode rotateRight(ListNode head, int k) {
            if (k == 0 || head == null || head.next == null) return head;
            ListNode p = head;
            int count = 1;
            while (p.next != null) {
                p = p.next;
                count++;
            }
            int offset = count - k % count;
            if (offset == 0) return head;

            // 闭合
            p.next = head;
            while (offset > 0) {
                p = p.next;
                offset--;
            }
            ListNode newHead = p.next;
            p.next = null;
            return newHead;
        }

    }

}
