package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1019nextLargerNodes {

    public static class Solution0 {

        /**
         单调栈：需要先反转链表 / O(N), O(N)
         */
        public int[] nextLargerNodes(ListNode head) {
            int n = 0;
            ListNode prev = null;
            while (head != null) {
                n++;
                ListNode next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }

            int[] res = new int[n];
            Deque<ListNode> s = new ArrayDeque<>();
            while (prev != null) {
                while (!s.isEmpty() && prev.val >= s.peek().val) {
                    s.pop();
                }
                res[--n] = s.isEmpty() ? 0 : s.peek().val;
                s.push(prev);
                prev = prev.next;
            }
            return res;
        }

    }

    public static class Solution1 {

        /**
         反转链表 + 单调栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int[] nextLargerNodes(ListNode head) {
            // 反转链表
            int n = 0;
            ListNode prev = null, cur = head;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
                n++;
            }

            // 单调栈求下一个更大元素
            int[] res = new int[n];
            int i = n - 1;
            Deque<Integer> s = new ArrayDeque<>();
            for (ListNode p = prev; p != null; p = p.next) {
                int val = p.val;
                while (!s.isEmpty() && s.peek() <= val) s.pop();
                res[i--] = s.isEmpty() ? 0 : s.peek();
                s.push(val);
            }
            return res;
        }

    }

}
