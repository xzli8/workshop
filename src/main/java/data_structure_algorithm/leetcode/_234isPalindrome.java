package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _234isPalindrome {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean isPalindrome(ListNode head) {
            // 快慢指针，快指针移动到尾时，慢指针移动到中间；慢指针移动过程中反转链表前半部分
            ListNode fast = head;
            ListNode slow = head;
            ListNode prev = null;
            while (null != fast && null != fast.next) {
                fast = fast.next.next;
                ListNode next = slow.next;
                slow.next = prev;
                prev = slow;
                slow = next;
            }

            // 节点个数为奇数个时，中间节点不参与比较
            if (null != fast) {
                slow = slow.next;
            }

            while (null != slow) {
                if (slow.val != prev.val) {
                    return false;
                }
                slow = slow.next;
                prev = prev.next;
            }
            return true;
        }

    }

}
