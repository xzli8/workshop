package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _142detectCycle {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            while (head != null) {
                set.add(head);
                head = head.next;
                if (set.contains(head)) {
                    return head;
                }
            }
            return null;
        }

    }



    public static class Solution2 {

        /**
         快慢指针(推导)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public ListNode detectCycle(ListNode head) {
             if (null == head) return null;

             ListNode fast = head;
             ListNode slow = head;
             while(null != fast && null != fast.next) {
                 slow = slow.next;
                 fast = fast.next.next;
                 if (slow == fast) {
                     slow = head;
                     while(slow != fast) {
                         slow = slow.next;
                         fast = fast.next;
                     }
                     return slow;
                 }
             }
             return null;
         }


    }



}
