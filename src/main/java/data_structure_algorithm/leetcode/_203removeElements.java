package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _203removeElements {

    public static class Solution1 {

        /**
         迭代：通过待删除节点的前一个节点来删除
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head), prev = dummy;
            while (prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                } else {
                    prev = prev.next;
                }
            }
            return dummy.next;
        }


    }



    public static class Solution2 {

    }

}
