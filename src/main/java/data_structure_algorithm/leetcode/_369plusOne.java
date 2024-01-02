package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _369plusOne {

    /**
     *  Description:
     *      Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
     *      You may assume the integer do not contain any leading zero, except the number 0 itself.
     *      The digits are stored such that the most significant digit is at the head of the list.
     *
     *  Example :
     *      Input: [1,2,3]
     *      Output: [1,2,4]
     *
     *  Nowcoder link: https://www.nowcoder.com/questionTerminal/a2f1105d2ac5466e9ba8fd61310ba6d1
     */

    public static class Solution1 {

        public ListNode plusOne(ListNode head) {
            if (head == null) return null;

            // 反转链表，从末位数字开始加1
            int carry = 1;
            ListNode tail = reverse(head), prev = null, cur = tail;
            while (cur != null) {
                int val = cur.val + carry;
                cur.val = val % 10;
                carry = val / 10;
                prev = cur;
                cur = cur.next;
            }
            if (carry != 0) prev.next = new ListNode(carry);
            return reverse(tail);
        }

        // 反转链表
        private ListNode reverse(ListNode head) {
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

    }

}
