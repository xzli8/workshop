package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class NC_207sortLinkedList {

    /**
     * nowcoder:https://www.nowcoder.com/practice/3a188e9c06ce4844b031713b82784a2a?tpId=117&tqId=39396&rp=1&ru=/exam/oj&qru=/exam/oj&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26pageSize%3D50%26search%3D207%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E7%25AF%2587%26topicId%3D117&difficulty=undefined&judgeStatus=undefined
     */

    public static class Solution1 {

        /**
         拆分 + 反转 + 合并
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode sortLinkedList (ListNode head) {
            // 奇偶拆分
            ListNode odd = new ListNode(0), even = new ListNode(0);
            ListNode p = head, p1 = odd, p2 = even;
            while (p != null && p.next != null) {
                p1.next = p;
                p2.next = p.next;
                p1 = p1.next;
                p2 = p2.next;
                p = p.next.next;
            }
            if (p != null) {
                p1.next = p;
                p1 = p1.next;
            }
            p1.next = null;
            p2.next = null;

            // 反转偶数链表
            ListNode prev = null, cur = even.next;
            even.next = null;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            // 合并两个升序链表
            ListNode dummy = new ListNode(0);
            p = dummy; p1 = odd.next; p2 = prev;
            while (p1 != null && p2 != null) {
                if (p1.val < p2.val) {
                    p.next = p1;
                    p1 = p1.next;
                } else {
                    p.next = p2;
                    p2 = p2.next;
                }
                p = p.next;
            }
            while (p1 != null) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            }
            while (p2 != null) {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
            return dummy.next;
        }

    }

}
