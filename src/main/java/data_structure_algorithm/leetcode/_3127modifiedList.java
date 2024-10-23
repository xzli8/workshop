package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _3127modifiedList {

    public static class Solution1 {

        /**
         HashSet
         */
        public ListNode modifiedList(int[] nums, ListNode head) {
            Set<Integer> s = new HashSet<>();
            for (int num : nums) {
                s.add(num);
            }

            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode p = dummy;
            while (p.next != null) {
                if (s.contains(p.next.val)) {
                    p.next = p.next.next;
                } else {
                    p = p.next;
                }
            }
            return dummy.next;
        }

    }

}
