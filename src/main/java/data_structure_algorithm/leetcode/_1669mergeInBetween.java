package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * 1669. 合并两个链表
 *
 * ref: https://leetcode.cn/problems/merge-in-between-linked-lists/
 *
 * 题目
 * - 把 list1 中下标 a 到 b 的节点删掉（下标从 0 开始），把 list2 整条接到这个位置
 * - 示例：list1 = [10,1,13,6,9,5]，a = 3，b = 4，list2 = [1000000,1000001,1000002]
 *     → [10,1,13,1000000,1000001,1000002,5]
 * - 原题保证 1 <= a <= b < len(list1) - 1（不会删头尾），list2 非空
 *
 * 面试变体（微软面试真题）
 * - 位置 I、J 从 1 开始，结果 = list1 的第 1 ~ I-1 个 + list2 + list1 的第 J+1 ~ 末尾
 * - 示例：list1 = 1->2->3->4->5，list2 = 6->7->8->9，I = 2，J = 3 → 1->6->7->8->9->4->5
 * - 不再保证不删头尾、list2 非空（Solution2）
 *
 * 通用步骤（三段拼接）
 * - 找到删除段的前一个节点 prev（要删头时 prev 是 dummy）
 * - 找到删除段的后一个节点 after（要删到末尾时 after 是 null）
 * - prev.next = list2 的头，list2 的尾.next = after
 * - list2 为空：直接 prev.next = after
 */
public class _1669mergeInBetween {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
            ListNode dummy = new ListNode(0, list1);
            ListNode prev = dummy;
            for (int i = 0; i < a; i++) {
                prev = prev.next;
            }
            ListNode next = prev.next;
            for (int i = a; i <= b; i++) {
                next = next.next;
            }
            prev.next = list2;
            ListNode p = list2;
            while (p.next != null) {
                p = p.next;
            }
            p.next = next;
            return dummy.next;
        }

    }

    public static class Solution2 {

        /**
         * 面试变体：I、J 从 1 开始，处理所有边界
         * - dummy 指向 list1：I = 1（删头）时 prev 就是 dummy，不用特判
         * - prev：从 dummy 走 I - 1 步，是第 I - 1 个节点
         * - after：从 prev 走 J - I + 2 步，是第 J + 1 个节点；J 是最后一个时为 null
         * - list2 为空：跳过找尾，直接 prev.next = after
         * - 参数非法（I < 1、I > J、J 超过长度）：抛异常，面试时先和面试官确认要不要处理
         * 时间复杂度：O(M + N)（M = list1 长度，N = list2 长度）
         * 空间复杂度：O(1)
         */
        public ListNode mergeInBetween(ListNode list1, int i, int j, ListNode list2) {
            if (i < 1 || i > j) throw new IllegalArgumentException("need 1 <= i <= j");
            ListNode dummy = new ListNode(0, list1);

            // 第 i - 1 个节点（i = 1 时是 dummy）
            ListNode prev = dummy;
            for (int k = 1; k < i; k++) {
                prev = prev.next;
                if (prev == null) throw new IllegalArgumentException("i out of range");
            }

            // 第 j + 1 个节点：先走到第 j 个，再取 next
            ListNode last = prev;
            for (int k = i; k <= j; k++) {
                last = last.next;
                if (last == null) throw new IllegalArgumentException("j out of range");
            }
            ListNode after = last.next;

            if (list2 == null) {
                prev.next = after;
            } else {
                prev.next = list2;
                ListNode tail = list2;
                while (tail.next != null) {
                    tail = tail.next;
                }
                tail.next = after;
            }
            return dummy.next;
        }

    }

    public static class TestMergeInBetween {

        private static ListNode list(int... vals) {
            return ListNode.buildWithArray(vals);
        }

        private static String str(ListNode head) {
            return head == null ? "" : head.toString();
        }

        @Test
        public void leetcodeExample() {
            ListNode res = new Solution1().mergeInBetween(list(10, 1, 13, 6, 9, 5), 3, 4, list(1000000, 1000001, 1000002));
            Assert.assertEquals("10->1->13->1000000->1000001->1000002->5", str(res));
        }

        @Test
        public void interviewExample() {
            ListNode res = new Solution2().mergeInBetween(list(1, 2, 3, 4, 5), 2, 3, list(6, 7, 8, 9));
            Assert.assertEquals("1->6->7->8->9->4->5", str(res));
        }

        @Test
        public void removeHead() {
            ListNode res = new Solution2().mergeInBetween(list(1, 2, 3, 4, 5), 1, 2, list(6, 7));
            Assert.assertEquals("6->7->3->4->5", str(res));
        }

        @Test
        public void removeTail() {
            ListNode res = new Solution2().mergeInBetween(list(1, 2, 3, 4, 5), 4, 5, list(6, 7));
            Assert.assertEquals("1->2->3->6->7", str(res));
        }

        @Test
        public void removeWholeList1() {
            ListNode res = new Solution2().mergeInBetween(list(1, 2, 3), 1, 3, list(6, 7));
            Assert.assertEquals("6->7", str(res));
        }

        @Test
        public void emptyList2() {
            ListNode res = new Solution2().mergeInBetween(list(1, 2, 3, 4, 5), 2, 3, null);
            Assert.assertEquals("1->4->5", str(res));
            Assert.assertNull(new Solution2().mergeInBetween(list(1, 2), 1, 2, null));
        }

        @Test(expected = IllegalArgumentException.class)
        public void jOutOfRange() {
            new Solution2().mergeInBetween(list(1, 2, 3), 2, 4, list(6));
        }

    }

}
