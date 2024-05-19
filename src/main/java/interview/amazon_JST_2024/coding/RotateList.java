package interview.amazon_JST_2024.coding;

import data_structure_algorithm.domain.ListNode;

public class RotateList {

    /**
     * Date: 2024.03.06 (Online interview)
     * Link: https://leetcode.cn/problems/rotate-list/description/
     */

    /**
     * Solution: 将链表闭合成环后再在合适的位置断开
     *  TC:O(N)
     *  SC:O(1)
     */
    public ListNode rotateList(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) return head;
        ListNode p = head;
        int count = 1;
        while (p.next != null) {
            p = p.next;
            count++;
        }
        int offset = count - k % count;
        if (offset == 0) return head;

        // 闭合
        p.next = head;
        while (offset > 0) {
            p = p.next;
            offset--;
        }
        ListNode newHead = p.next;
        p.next = null;
        return newHead;
    }

}
