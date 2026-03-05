package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

public class _725splitListToParts {

    public static class Solution0 {

        /**
         计数+划分: O(N), O(1)
         If there are N nodes in the list, and k parts, then every part has N/k elements, except the first N%k parts have an extra one.
         */
        public ListNode[] splitListToParts(ListNode head, int k) {
            int n = 0;
            ListNode p = head;
            while (p != null) {
                n++;
                p = p.next;
            }

            p = head;
            ListNode[] res = new ListNode[k];
            for (int i = 0; i < n % k; i++) {
                ListNode subHead = p, prev = null;
                for (int j = 0; j < n / k + 1; j++) {
                    prev = p;
                    p = p.next;
                }
                prev.next = null;
                res[i] = subHead;
            }

            for (int i = n % k; i < k; i++) {
                ListNode subHead = p, prev = null;
                for (int j = 0; j < n / k; j++) {
                    prev = p;
                    p = p.next;
                }
                if (prev != null) prev.next = null;
                res[i] = subHead;
            }
            return res;
        }

    }

    public static class Solution1 {

        /**
         先计算链表长度，然后分隔
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode[] splitListToParts(ListNode head, int k) {
            // 计算链表长度
            ListNode p = head;
            int n = 0;
            while (p != null) {
                n++;
                p = p.next;
            }

            // 分隔成k部分，每部分n/k个元素，前n%k个部分多一个元素
            ListNode[] res = new ListNode[k];
            int subLen = n / k;
            int extraOne = n % k;
            p = head;
            for (int i = 0; i < extraOne; i++) {
                ListNode subHead = p, prev = null;
                int count = 0;
                while (count < subLen + 1) {
                    prev = p;
                    p = p.next;
                    count++;
                }
                if (prev != null) prev.next = null;
                res[i] = subHead;
            }
            for (int i = extraOne; i < k; i++) {
                ListNode subHead = p, prev = null;
                int count = 0;
                while (count < subLen) {
                    prev = p;
                    p = p.next;
                    count++;
                }
                if (prev != null) prev.next = null;
                res[i] = subHead;
            }
            return res;
        }

    }

}
