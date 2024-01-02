package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _445addTwoNumbers {

    public static class Solution1 {

        /**
         反转链表后模拟
         时间复杂度：O(len1 + len2)
         空间复杂度：O(1)
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            // 反转链表
            l1 = reverseList(l1);
            l2 = reverseList(l2);

            ListNode dummy = new ListNode();
            ListNode p = dummy;
            int add = 0;
            while (l1 != null || l2 != null || add != 0) {
                int val = getVal(l1) + getVal(l2) + add;
                add = val / 10;
                val %= 10;

                ListNode tmp = new ListNode(val);
                tmp.next = p.next;
                p.next = tmp;
                p = p.next;

                l1 = getNext(l1);
                l2 = getNext(l2);
            }
            return reverseList(dummy.next);
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            return prev;
        }

        private int getVal(ListNode l) {
            return l == null ? 0 : l.val;
        }

        private ListNode getNext(ListNode l) {
            return l == null ? null : l.next;
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
