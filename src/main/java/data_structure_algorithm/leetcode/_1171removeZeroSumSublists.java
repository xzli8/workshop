package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.HashMap;
import java.util.Map;

public class _1171removeZeroSumSublists {

    public static class Solution1 {

        /**
         哈希表(类似前缀和)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public ListNode removeZeroSumSublists(ListNode head) {
            // 添加哑元节点（删除的元素可能是第一个，简化编程逻辑）
            ListNode dummy = new ListNode(0, head);

            // 遍历链表，建立sum到节点的映射（两次出现相同的sum，前一个节点会被后一个节点覆盖）
            Map<Integer, ListNode> map = new HashMap<>();
            int sum = 0;
            for (ListNode p = dummy; p != null; p = p.next) {   // 注意循环的起点是dummy，不是head
                sum += p.val;
                map.put(sum, p);
            }

            // 再次遍历链表，若当前sum在map中能找到对应节点，说明该节点和map中找到节点之间的节点和为0，删除
            sum = 0;
            for (ListNode p = dummy; p != null; p = p.next) {
                sum += p.val;
                p.next = map.get(sum).next;
            }
            return dummy.next;
        }

    }

}
