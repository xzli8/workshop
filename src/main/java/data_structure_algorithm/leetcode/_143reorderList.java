package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

public class _143reorderList {

    public static class Solution1 {

        /**
         借助列表存储（链表不能随机访问，而线性表可以），双指针分别从前后遍历
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public void reorderList(ListNode head) {
             List<ListNode> list = new ArrayList<>();
             ListNode p = head;
             while (p != null) {
                 list.add(p);
                 p = p.next;
             }

             int i = 0, j = list.size()-1;
             while (i < j) {
                 list.get(i).next = list.get(j);
                 i++;
                 if (i == j) break; // 链表元素个数为偶数个时，提前退出
                 list.get(j).next = list.get(i);
                 j--;
             }
             list.get(i).next = null;
         }

    }



    public static class Solution2 {

        /**
         寻找中点 + 反转后半部分 + 合并
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void reorderList(ListNode head) {
            if (head == null || head.next == null) return;

            // 快慢指针寻找链表中点
            ListNode fast = head;
            ListNode slow = head;

            // 这里注意需要将链表的前半部分和后半部分断开，所以这里实际寻找的不是链表中点，而是链表中点的前驱节点
            ListNode prev = null;
            while (fast != null && fast.next != null) {
                prev = slow;
                slow = slow.next;
                fast = fast.next.next;
            }
            prev.next = null;   // 断开前后两部分

            // 反转后半部分
            prev = null;
            ListNode cur = slow;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }

            ListNode tailPrev = null;
            while (head != null && prev != null) {
                ListNode headNext = head.next;
                ListNode prevNext = prev.next;
                head.next = prev;
                prev.next = headNext;
                head = headNext;
                tailPrev = prev;
                prev = prevNext;
            }
            // 当链表中元素个数为奇数个时，后半部分会多一个元素，所以要添加到末尾
            if (prev != null)  tailPrev.next = prev;
        }

    }


}
