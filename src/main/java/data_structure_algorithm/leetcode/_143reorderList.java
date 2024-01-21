package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _143reorderList {

    public static class Solution1 {

        /**
         快慢指针找中点 + 反转后半部分 + 合并
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void reorderList(ListNode head) {
            // 边界情况
            if (head == null || head.next == null) return;

            // 快慢指针找中点
            ListNode prev = null, slow = head, fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;   // 将前后两部分断开

            // 反转后半部分
            ListNode cur = slow;
            prev = null;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // 合并
            ListNode dummy = new ListNode(), p = dummy, p1 = head, p2 = prev;
            while (p1 != null && p2 != null) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
                p.next = p2;
                p = p.next;
                p2 = p2.next;
            }
            if (p2 != null) p.next = p2;    // 当链表节点数量为奇数个时，后半部分会多一个节点
        }

    }

}
