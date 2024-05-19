package interview.amazon_JST_2024.coding;

import data_structure_algorithm.domain.ListNode;
import org.junit.Test;

public class LongestIncreasingSublist {

    /**
     * Date: 2024.03.02 (OA)
     * Description: Given a one-way linked list of integers, you need to return the longest increasing subsequence as a
     * new linked list. For example, the given list is: 1->5->4->3->6->8->12->10 The new one should be: 3->6->8->12.
     * Link: https://stackoverflow.com/questions/68344360/longest-increasing-subsequence-in-a-linked-list-of-integers
     */

    /**
     * Solution: Sliding window
     *  TC:O(N)
     *  SC:O(1)
     */
    public ListNode longestIncreasingSublist(ListNode head) {
        // boundary condition
        if (null == head || head.next == null) return head;

        // sliding window
        int curLen = 1, maxLen = 1;
        ListNode left = head, right = head.next, res = head;
        while (right != null) {
            while (right.next != null && right.next.val > right.val) {
                right = right.next;
                curLen++;
            }
            if (curLen > maxLen) {
                maxLen = curLen;
                res = left;
            }
            right = right.next;
            left = right;
            curLen = 1;
        }

        // remove redundant nodes at the tail
        ListNode cur = res;
        for (int i = 1; i < maxLen; i++) {
            cur = cur.next;
        }
        cur.next = null;
        return res;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(10), node2 = new ListNode(12, node1), node3 = new ListNode(8, node2),
                node4 = new ListNode(6, node3), node5 = new ListNode(3, node4), node6 = new ListNode(4, node5),
                node7 = new ListNode(5, node6), node8 = new ListNode(1, node7);
        ListNode res = longestIncreasingSublist(node8);
        for (ListNode node = res; node != null; node = node.next) {
            System.out.println(node.val);
        }

        // Todo: more test cases
    }

}
