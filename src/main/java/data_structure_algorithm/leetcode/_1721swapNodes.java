package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

public class _1721swapNodes {

    public static class Solution0 {

        /**
         用双指针交换链表节点的值: O(N), O(1)
         */
        // public ListNode swapNodes(ListNode head, int k) {
        //     ListNode kth = head;
        //     for (int i = 1; i < k; i++) {
        //         kth = kth.next;
        //     }

        //     ListNode kthToLast = head, cur = kth;
        //     while (cur.next != null) {
        //         cur = cur.next;
        //         kthToLast = kthToLast.next;
        //     }

        //     int tmp = kth.val;
        //     kth.val = kthToLast.val;
        //     kthToLast.val = tmp;
        //     return head;
        // }

        /**
         交换实际节点: O(N), O(1)
         */
        public ListNode swapNodes(ListNode head, int k) {
            ListNode dummy = new ListNode(0, head), kth = head, prevKth = dummy;
            for (int i = 1; i < k; i++) {
                kth = kth.next;
                prevKth = prevKth.next;
            }

            ListNode kthToLast = head, prevKthToLast = dummy, cur = kth;
            while (cur.next != null) {
                cur = cur.next;
                kthToLast = kthToLast.next;
                prevKthToLast = prevKthToLast.next;
            }

            // corner case: 待交换的两节点相邻时要特殊处理，否则会形成环
            if (kth == kthToLast) {
                return dummy.next;
            } else if (kth.next == kthToLast) {
                prevKth.next = kthToLast;
                kth.next = kthToLast.next;
                kthToLast.next = kth;
            } else if (kthToLast.next == kth) {
                prevKthToLast.next = kth;
                kthToLast.next = kth.next;
                kth.next = kthToLast;
            } else {
                // general case
                ListNode kthNext = kth.next;
                kth.next = kthToLast.next;
                kthToLast.next = kthNext;
                prevKth.next = kthToLast;
                prevKthToLast.next = kth;
            }
            return dummy.next;
        }

    }



    public static class Solution1 {

        /**
         TwoPointers
         TimeComplexity: O(N)
         SpaceComplexity: O(N)
         */
         public ListNode swapNodes(ListNode head, int k) {
             // traverse the linked list and store the elements in an array
             List<Integer> list = new ArrayList<>();
             while (head != null) {
                 list.add(head.val);
                 head = head.next;
             }

             // use two pointers to swap
             int n = list.size();
             int tmp = list.get(k - 1);
             list.set(k - 1, list.get(n - k));
             list.set(n - k, tmp);

             // rebuild the linked list with the list
             ListNode dummy = new ListNode(), node = dummy;
             for (int i = 0; i < n; i++) {
                 node.next = new ListNode(list.get(i));
                 node = node.next;
             }
             return dummy.next;
         }

    }



    public static class Solution2 {

        /**
         swap the values of two nodes only using two pointers
         TimeComplexity: O(N)
         SpaceComplexity: O(1)
         */
        public ListNode swapNodes(ListNode head, int k) {
            // find k-th
            ListNode kth = head;
            for (int i = 1; i < k; i++) {
                kth = kth.next;
            }

            // find k-th to last
            ListNode kthToLast = head, cur = kth;
            while (cur.next != null) {
                cur = cur.next;
                kthToLast = kthToLast.next;
            }

            // swap the values of two nodes
            int tmp = kth.val;
            kth.val = kthToLast.val;
            kthToLast.val = tmp;
            return head;
        }

    }

}
