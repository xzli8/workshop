package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class _445addTwoNumbers {

    public static class Solution1 {

        /**
         模拟：先反转链表，然后模拟整数加法
         时间复杂度：O(N1 + N2)
         空间复杂度：O(1)
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            int carry = 0;
            ListNode p1 = reverse(l1), p2 = reverse(l2), dummy = new ListNode(), p = dummy;
            while (p1 != null || p2 != null || carry != 0) {
                int val = getVal(p1) + getVal(p2) + carry;
                p.next = new ListNode(val % 10);
                p = p.next;
                carry = val / 10;
                p1 = getNext(p1);
                p2 = getNext(p2);
            }
            return reverse(dummy.next);
        }

        private int getVal(ListNode p) {
            return p == null ? 0 : p.val;
        }

        private ListNode getNext(ListNode p) {
            return p == null ? null : p.next;
        }

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



    public static class Solution2 {

        /**
         用栈来代替反转链表
         时间复杂度：O(len1 + len2)
         空间复杂度：O(len1 + len2)
         */
         public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

             Deque<Integer> s1 = new ArrayDeque<>();
             while (l1 != null) {
                 s1.push(l1.val);
                 l1 = l1.next;
             }

             Deque<Integer> s2 = new ArrayDeque<>();
             while (l2 != null) {
                 s2.push(l2.val);
                 l2 = l2.next;
             }

             Deque<Integer> s3 = new ArrayDeque<>();
             int add = 0;
             while (!s1.isEmpty() || !s2.isEmpty() || add != 0) {
                 int val = getVal(s1) + getVal(s2) + add;
                 add = val / 10;
                 val %= 10;
                 s3.push(val);
             }

             ListNode dummy = new ListNode();
             ListNode p = dummy;
             while (!s3.isEmpty()) {
                 ListNode tmp = new ListNode(s3.pop());
                 tmp.next = p.next;
                 p.next = tmp;
                 p = p.next;
             }
             return dummy.next;
         }

         private int getVal(Deque<Integer> s) {
             return s.isEmpty() ? 0 : s.pop();
         }

    }

}
