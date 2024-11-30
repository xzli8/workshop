package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _21mergeTwoLists {

    public static class Solution1 {

        /**
         TwoPointers
         T: O(N)
         S: O(1)
         */
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            if (list1 == null) return list2;
            if (list2 == null) return list1;
            ListNode dummy = new ListNode(), p = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    p.next = list1;
                    list1 = list1.next;
                } else {
                    p.next = list2;
                    list2 = list2.next;
                }
                p = p.next;
            }
            if (list1 != null) p.next = list1;
            if (list2 != null) p.next = list2;
            return dummy.next;
        }

    }

}
