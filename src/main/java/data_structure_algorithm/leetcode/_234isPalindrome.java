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



    public static class Solution2 {

        /**
         双指针：快慢指针找中点，然后反转前半部分，再逐一比较
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }

            // 快慢指针找中点
            ListNode fast = head, slow = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;

            // 反转前半部分
            prev = null;
            while (head != null) {
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }

            // 逐一比较
            if (fast != null) slow = slow.next;     // 链表节点是奇数个，跳过中间节点
            while (prev != null) {
                if (prev.val != slow.val) return false;
                prev = prev.next;
                slow = slow.next;
            }
            return true;
        }

    }

}
