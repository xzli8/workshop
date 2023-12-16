package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _25reverseKGroup {

    public static class Solution1 {

        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode dummy = new ListNode();
            dummy.next = head;

            ListNode lastTail = dummy;
            ListNode cur = head;
            ListNode tail = findTail(cur, k);
            while(tail != null) {
                lastTail.next = tail;
                lastTail = cur;

                ListNode nextHead = tail.next;
                ListNode prev = nextHead;
                while(cur != nextHead) {
                    ListNode next = cur.next;
                    cur.next = prev;
                    prev = cur;
                    cur = next;
                }
                tail = findTail(nextHead, k);
            }
            return dummy.next;
        }

        // 定位每次翻转的尾节点
        private ListNode findTail(ListNode cur, int k) {
            for (int i = 1; i < k; i++) {
                if (cur == null) return null;
                cur = cur.next;
            }
            return cur;
        }

    }

}
