package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _23mergeKLists {

    public static class Solution1 {

        /**
         优先队列
         时间复杂度：O(kn * logk)
         空间复杂度：O(k)
         */
        public ListNode mergeKLists(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
            for (ListNode list : lists) {
                if (list != null) pq.offer(list);
            }

            ListNode head = new ListNode();
            ListNode tail = head;
            while (!pq.isEmpty()) {
                ListNode node = pq.poll();
                tail.next = node;
                tail = tail.next;
                if (node.next != null) {
                    pq.offer(node.next);
                }
            }
            return head.next;
        }


    }



    public static class Solution2 {

        /**
         合并两个有序链表(leetcode.21)
         */
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    p.next = list1;
                    list1 = list1.next;
                } else {
                    p.next = list2;
                    list2 = list2.next;
                }
                p = p.next;
            }
            while (list1 != null) {
                p.next = list1;
                list1 = list1.next;
                p = p.next;
            }
            while (list2 != null) {
                p.next = list2;
                list2 = list2.next;
                p = p.next;
            }
            return dummy.next;
        }

        /**
         两两合并（分治）
         时间复杂度：O(kn * logk)
         空间复杂度：O(logk)
         */
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            return merge(lists, 0, lists.length - 1);
        }

        public ListNode merge(ListNode[] lists, int low, int high) {
            if (low == high) return lists[low];
            int mid = ((high - low) >> 1) + low;
            ListNode list1 = merge(lists, low, mid);
            ListNode list2 = merge(lists, mid + 1, high);
            return mergeTwoLists(list1, list2);
        }

    }



    public static class Solution3 {

        /**
         合并两个有序链表(leetcode.21)
         */
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            while (list1 != null && list2 != null) {
                if (list1.val < list2.val) {
                    p.next = list1;
                    list1 = list1.next;
                } else {
                    p.next = list2;
                    list2 = list2.next;
                }
                p = p.next;
            }
            while (list1 != null) {
                p.next = list1;
                list1 = list1.next;
                p = p.next;
            }
            while (list2 != null) {
                p.next = list2;
                list2 = list2.next;
                p = p.next;
            }
            return dummy.next;
        }

        /**
            逐一合并(时空复杂度指标：k-lists.length; n-链表中最多的元素数目)
                时间复杂度：O(k^2 * n)
                空间复杂度：O(1)
        */
        public ListNode mergeKLists(ListNode[] lists) {
            ListNode ans = null;
            for (int i = 0; i < lists.length; i++) {
                ans = mergeTwoLists(ans, lists[i]);
            }
            return ans;
        }

    }



}
