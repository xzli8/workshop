package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _82deleteDuplicates {

    public static class Solution1 {

        /**
         要删除链表中的某个节点，需要定位到该节点的前驱节点（注意链表的头节点可能被删除，可以添加dummy节点简化编程）
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode deleteDuplicates(ListNode head) {
            ListNode dummy = new ListNode(0, head), p = dummy;
            while (p.next != null) {
                ListNode tmp = p.next;
                while (tmp.next != null && tmp.next.val == tmp.val) {
                    tmp = tmp.next;
                }
                if (tmp != p.next) p.next = tmp.next;
                else p = p.next;
            }
            return dummy.next;
        }

    }

}
