package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _148sortList {

    public static class Solution1 {


        /**
         分析：
         题目要求时间复杂度为O(nlogn)，归并排序的时间复杂度固定为O(nlogn)，
         快速排序的最坏时间复杂度为O(n^2)，平均时间复杂度为O(nlogn)
         对于数组而言，归并排序空间复杂度为O(n)；但对于链表而言，其空间复杂度可以为O(1)
         如果采用自上而下的分治做法，因为递归有栈空间的开销，严格意义上来说其空间复杂度为O(logn)，并不是O(1)
         如果采用自下而上的归并做法，因为不涉及递归的栈空间开销，所以其空间复杂度为O(1)
         下面将分别使用自上而下的分治和自下而上的归并，其中两种方法均要用到合并两个有序链表
         */


        // 合并两个有序链表（leetcode21）
        private ListNode merge(ListNode p1, ListNode p2) {
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            while (p1 != null) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            }
            while (p2 != null) {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
            return dummy.next;
        }

        /**
         自上而下分治（递归）
         1.快慢指针找链表中点，将链表一分为二，直至链表只包含一个节点或为空
         2.合并两个有序链表（参考leetcode21）
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            // 快慢指针找链表中点
            ListNode prev = null;
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null; // 断开前后两部分

            // 递归
            ListNode p1 = sortList(head);
            ListNode p2 = sortList(slow);

            // 合并
            return merge(p1, p2);
        }

    }

}
