package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

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
         推导：
         - 记号
             - a：头节点到入环点的距离
             - b：入环点到相遇点的距离（顺着环走）
             - c：相遇点继续走回入环点的距离
             - 环长 L = b + c
         - 第一步：相遇时各走了多少
             - slow 走了 a + b（slow 进环时 fast 已在环内，间距 < L，fast 每步追近 1，不到 L 步就追上，所以 slow 在环里走不满一圈）
             - fast 走了 a + b + nL（比 slow 多绕 n 圈，n >= 1）
         - 第二步：fast 路程是 slow 的 2 倍
             - a + b + nL = 2(a + b)
             - a = nL - b = (n - 1)L + c
         - 第三步：找入环点
             - slow 回到头节点，fast 留在相遇点，两者都每次走 1 步
             - 走 a 步后：slow 正好到入环点；fast 走了 c + (n - 1)L（先走 c 到入环点，再绕 n - 1 整圈，仍在入环点）
             - 所以两者第一次相遇的位置就是入环点
         */
         public ListNode detectCycle(ListNode head) {
             if (null == head) return null;

             ListNode fast = head, slow = head;
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
