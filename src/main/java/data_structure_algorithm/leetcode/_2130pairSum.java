package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _2130pairSum {

    public static class Solution1 {

        /**
         找链表中点，反转后半部分，然后遍历求和
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int pairSum(ListNode head) {
            // 快慢指针找链表中点
            ListNode fast = head, slow = head, prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;

            // 反转后半部分
            prev = null;
            ListNode cur = slow;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // 遍历求和，找最大值
            int maxPairSum = 0;
            ListNode p1 = head, p2 = prev;
            while (p1 != null && p2 != null) {
                maxPairSum = Math.max(maxPairSum, p1.val + p2.val);
                p1 = p1.next;
                p2 = p2.next;
            }
            return maxPairSum;
        }

    }

}
