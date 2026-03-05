package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _24swapPairs {

    public static class Solution0 {

        /**
         迭代: O(N), O(1)
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }

            ListNode dummy = new ListNode(0, head), prev = dummy, cur = head;
            while (cur != null && cur.next != null) {
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = cur;
                prev.next = next;
                prev = cur;
                cur = cur.next;
            }
            return dummy.next;
        }

    }

    public static class Solution1 {

        /**
         迭代: O(N), O(1)
         */
         public ListNode swapPairs(ListNode head) {

             // 哨兵节点
             ListNode dummy = new ListNode();
             dummy.next = head;

             ListNode prev = dummy;
             ListNode cur = head;
             while (cur != null && cur.next != null) {
                 // 交换
                 ListNode next = cur.next;
                 cur.next = next.next;
                 next.next = cur;
                 prev.next = next;

                 // 游标后移
                 prev = cur;
                 cur = cur.next;
             }
             head = dummy.next;
             return head;
         }

    }



    public static class Solution2 {

        /**
         递归: O(N), O(N)
         */
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode next = head.next;
            head.next = swapPairs(next.next);
            next.next = head;
            return next;
        }

    }

}
