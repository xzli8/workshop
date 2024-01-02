package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2addTwoNumbers {

    public static class Solution1 {

        /**
         模拟（由于链表数字是逆序存储的，所以不需要反转链表）
         1.每次加法会产生进位值和当前位值
         2.用哑节点简化编程

         时间复杂度：O(max(len1, len2))
         空间复杂度：O(1)
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode l3 = dummy;
            int add = 0;
            while (l1 != null || l2 != null || add != 0) {
                int val = getVal(l1) + getVal(l2) + add;
                add = val / 10;
                val %= 10;

                ListNode tmp = new ListNode(val, l3.next);
                l3.next = tmp;
                l3 = l3.next;
                l1 = getNext(l1);
                l2 = getNext(l2);
            }
            return dummy.next;
        }

        private int getVal(ListNode l) {
            return l == null ? 0 : l.val;
        }

        private ListNode getNext(ListNode l) {
            return l == null ? null : l.next;
        }

    }

}
