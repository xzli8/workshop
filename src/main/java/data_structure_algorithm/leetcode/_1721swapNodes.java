package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

public class _1721swapNodes {

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
         SpaceComplexity: O(N)
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
