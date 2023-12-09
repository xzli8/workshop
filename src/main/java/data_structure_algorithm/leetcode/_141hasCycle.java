package data_structure_algorithm.leetcode;

import data_structure_algorithm.leetcode.domain.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _141hasCycle {

    /**
     * Definition for singly-linked list.
     */
    public static class Solution1 {

        /**
         哈希表：用哈希表记录走过的节点
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean hasCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            while (head != null) {
                set.add(head);
                head = head.next;
                if (set.contains(head)) {
                    return true;
                }
            }
            return false;
        }

    }



    public static class Solution2 {

        /**
         快慢指针：slow一次走一格，fast一次走两格，若有环则相遇，若无环则不相遇
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public boolean hasCycle(ListNode head) {
             if (null == head) return false;

             ListNode fast = head;
             ListNode slow = head;
             while(null != fast && null != fast.next) {
                 slow = slow.next;
                 fast = fast.next.next;
                 if (fast == slow) return true;
             }
             return false;
         }

    }



}
