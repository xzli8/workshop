package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

public class _160getIntersectionNode {

    public static class Solution1 {

        /**
         双指针（在第一轮移动后，消除了A、B从头到交点的长度差）
         p1指向A头部，p2指向B头部；
         移动p1, p2，当p1到达A尾后，令p1指向B头，当p2到达B尾后，令p2指向A头
         继续移动p1, p2，直到两者相等，即为交点
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode p1 = headA;
            ListNode p2 = headB;
            while (p1 != p2) {
                p1 = p1 == null ? headB : p1.next;
                p2 = p2 == null ? headA : p2.next;
            }
            return p1;
        }

    }

}
