package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _203removeElements {

    public static class Solution1 {

        /**
         迭代：通过待删除节点的前一个节点来删除
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head);
            ListNode p = dummy;
            while(null != p.next) {
                if (p.next.val == val) p.next = p.next.next;
                else p = p.next;
            }
            return dummy.next;
        }

    }



    public static class Solution2 {

    }

}
