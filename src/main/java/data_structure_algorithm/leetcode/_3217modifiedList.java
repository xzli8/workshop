package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _3217modifiedList {

    public static class Solution1 {

        /**
         哈希表+前驱节点：O(N), O(M)
         */
        public ListNode modifiedList(int[] nums, ListNode head) {
            Set<Integer> s = new HashSet<>();
            for (int num : nums) {
                s.add(num);
            }

            ListNode dummy = new ListNode(0, head), prev = dummy;
            while (prev.next != null) {
                if (s.contains(prev.next.val)) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummy.next;
        }

    }

}
