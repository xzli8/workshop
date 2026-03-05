package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _1836deleteDuplicatesUnsorted {

    /**
     * Given the head of a linked list, find all the values that appear more than once in the list and delete the nodes that have any of those values.
     * Return the linked list after the deletions.
     *
     * ref: https://www.cnblogs.com/cnoodle/p/14770566.html
     */

    /**
     * 两次扫描：第一遍哈希表统计，第二遍删除 / O(N), O(N)
     */
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode dummy = new ListNode(0, head), prev = dummy;

        // 第一次扫描：统计val出现次数
        Map<Integer, Integer> val2Count = new HashMap<>();
        while (head != null) {
            val2Count.put(head.val, val2Count.getOrDefault(head.val, 0) + 1);
            head = head.next;
        }

        // 第二次扫描：删除val出现次数超过1的节点
        while (prev.next != null) {
            if (val2Count.get(prev.next.val) > 1) {
                prev.next = prev.next.next;
            }
            else {
                prev = prev.next;
            }
        }
        return dummy.next;
    }

    @Test
    public void test() {
        System.out.println(deleteDuplicatesUnsorted(ListNode.buildWithArray(new int[] {1,2,3,2})));
    }



}
