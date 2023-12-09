package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _147insertionSortList {

    public static class Solution1 {

        /**
         new一个dummy节点表示新的排序好的链表，对待排序链表中的每个节点，从排序好的链表头开始遍历寻找插入位置
         时间复杂度：O(n^2)
         空间复杂度：O(1)
         */
        public ListNode insertionSortList(ListNode head) {
            ListNode dummy = new ListNode();
            while (head != null) {
                ListNode tmp = head;
                head = head.next;

                ListNode p = dummy;
                while (p.next != null) {
                    if (tmp.val < p.next.val) break;
                    else p = p.next;
                }
                tmp.next = p.next;
                p.next = tmp;
            }
            return dummy.next;
        }

    }

}
