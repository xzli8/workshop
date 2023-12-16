package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _92reverseBetween {

    public static class Solution1 {

        /**
         先找到待反转部分的头尾节点，然后调整指针进行反转
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            // 添加哑元节点
            ListNode dummy = new ListNode(0, head);

            // 找待反转部分的前驱节点
            ListNode prev = dummy;
            for (int i = 0; i < left - 1; i++) {
                prev = prev.next;
            }

            // 找待反转部分的尾节点
            ListNode tail = prev;
            for (int i = left; i <= right; i++) {
                tail = tail.next;
            }

            // 从当前节点开始反转
            ListNode cur = prev.next, tailNext = tail.next;
            prev.next = tail;
            prev = tailNext;
            while (cur != tailNext) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return dummy.next;
        }

    }



    public static class Solution2 {

        /**
         一趟扫描：先定位要反转部分的前驱节点，然后固定prev和cur节点，将next节点不断交换到前面来
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode dummy = new ListNode(0, head);

            // 定位待反转部分的前驱节点
            ListNode prev = dummy;
            for (int i = 0; i < left-1; i++) {
                prev = prev.next;
            }
            head = prev.next;

            // 逐个调整（prev和head的指向节点保持不变，不断将head.next指向的节点移到prev后面）
            for (int i = left; i < right; i++) {
                ListNode next = head.next;
                head.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            return dummy.next;
        }


    }

}
