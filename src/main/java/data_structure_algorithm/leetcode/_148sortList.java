package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _148sortList {


    /**
     分析：
     题目要求时间复杂度为O(nlogn)，归并排序的时间复杂度固定为O(nlogn)，
     快速排序的最坏时间复杂度为O(n^2)，平均时间复杂度为O(nlogn)
     对于数组而言，归并排序空间复杂度为O(n)；但对于链表而言，其空间复杂度可以为O(1)
     如果采用自上而下的分治做法，因为递归有栈空间的开销，严格意义上来说其空间复杂度为O(logn)，并不是O(1)
     如果采用自下而上的归并做法，因为不涉及递归的栈空间开销，所以其空间复杂度为O(1)
     下面将分别使用自上而下的分治和自下而上的归并，其中两种方法均要用到合并两个有序链表
     */

    public static class Solution0 {

        /**
         迭代归并：O(NlogN), O(1)
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            int length = getListLength(head);
            ListNode dummy = new ListNode(0, head);
            for (int size = 1; size < length; size <<= 1) {
                ListNode prev = dummy, cur = dummy.next;
                while (cur != null) {
                    ListNode list1 = cur, list2 = splitList(cur, size);
                    cur = splitList(list2, size);   // 下一轮循环的起始节点
                    ListNode merged = mergeTwoLists(list1, list2);
                    prev.next = merged;
                    while (prev.next != null) {
                        prev = prev.next;
                    }
                }
            }
            return dummy.next;
        }

        private int getListLength(ListNode head) {
            int length = 0;
            while (head != null) {
                length++;
                head = head.next;
            }
            return length;
        }

        // 从head开始切size个节点，返回切完后剩余链表的头节点
        private ListNode splitList(ListNode head, int size) {
            ListNode prev = null;
            for (int i = 0; i < size; i++) {
                if (head == null) return null;
                prev = head;
                head = head.next;
            }
            prev.next = null;
            return head;
        }

        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(), prev = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    prev.next = list1;
                    list1 = list1.next;
                    prev = prev.next;
                } else {
                    prev.next = list2;
                    list2 = list2.next;
                    prev = prev.next;
                }
            }
            if (list1 != null) prev.next = list1;
            if (list2 != null) prev.next = list2;
            return dummy.next;
        }

    }



    public static class Solution1 {

        /**
         递归归并: O(NlogN), O(logN)[递归函数调用的栈开销]
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode fast = head, slow = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;

            ListNode list1 = sortList(head), list2 = sortList(slow);
            return mergeTwoLists(list1, list2);
        }

        private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(), prev = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    prev.next = list1;
                    list1 = list1.next;
                    prev = prev.next;
                } else {
                    prev.next = list2;
                    list2 = list2.next;
                    prev = prev.next;
                }
            }
            if (list1 != null) prev.next = list1;
            if (list2 != null) prev.next = list2;
            return dummy.next;
        }

    }



    public static class Solution2 {

        /**
         插入排序(类似147)：O(N^2), O(1)，超时
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode dummy = new ListNode();
            while (head != null) {
                ListNode prev = dummy;
                while (prev.next != null && head.val > prev.next.val) {
                    prev = prev.next;
                }
                ListNode next = head.next;
                head.next = prev.next;
                prev.next = head;
                head = next;
            }
            return dummy.next;
        }

    }

}
