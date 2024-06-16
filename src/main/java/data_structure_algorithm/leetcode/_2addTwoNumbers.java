package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2addTwoNumbers {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(), p = dummy, p1 = l1, p2 = l2;
            int carry = 0;
            while (p1 != null || p2 != null || carry != 0) {
                int val = getVal(p1) + getVal(p2) + carry;
                ListNode newNode = new ListNode(val % 10);
                p.next = newNode;
                p = p.next;
                carry = val / 10;
                p1 = getNext(p1);
                p2 = getNext(p2);
            }
            return dummy.next;
        }

        private int getVal(ListNode p) {
            return p == null ? 0 : p.val;
        }

        private ListNode getNext(ListNode p) {
            return p == null ? null : p.next;
        }

    }



    public static class Solution2 {

        /**
         模拟(竖式加法)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode p1 = l1, p2 = l2, dummy = new ListNode(), p3 = dummy;
            int carry = 0;
            while (p1 != null || p2 != null || carry != 0) {
                int n1 = 0;
                if (p1 != null) {
                    n1 = p1.val;
                    p1 = p1.next;
                }
                int n2 = 0;
                if (p2 != null) {
                    n2 = p2.val;
                    p2 = p2.next;
                }
                int sum = n1 + n2 + carry;
                p3.next = new ListNode(sum % 10);
                p3 = p3.next;
                carry = sum / 10;
            }
            return dummy.next;
        }

    }

}
