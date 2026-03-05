package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.HashMap;
import java.util.Map;

public class _3063frequenciesOfElements {

    /**
     * ref: https://leetcode.doocs.org/lc/3063/#_1
     *
     * 题目描述：给定包含 k 个 不同 元素的链表的 head 节点，创建一个长度为 k 的链表，以 任何顺序 返回链表中所有 不同元素 出现的 频率。返回这个链表的头节点。
     *
     * 示例 1:
     *
     * 输入：head = [1,1,2,1,2,3]
     *
     * 输出：[3,2,1]
     *
     * 解释：列表中有 3 个不同的元素。1 的频率是 3，2 的频率是 2，3 的频率是 1。因此，我们返回 3 -> 2 -> 1。
     *
     * 注意 1 -> 2 -> 3，1 -> 3 -> 2，2 -> 1 -> 3，2 -> 3 -> 1，和 3 -> 1 -> 2 都是合法的答案。
     * 示例 2:
     *
     * 输入：head = [1,1,2,2,2]
     *
     * 输出：[2,3]
     *
     * 解释：列表中有 2 个不同的元素。1 和 2 出现的频率是 2 和 3。因此，我们返回 2 -> 3。
     * 示例 3:
     *
     * 输入：head = [6,5,4,3,2,1]
     *
     * 输出：[1,1,1,1,1,1]
     *
     * 解释：列表中有 6 个不同的元素。每个元素的频率是 1。因此，我们返回 1 -> 1 -> 1 -> 1 -> 1 -> 1。
     *
     *
     * 提示：
     *
     * 链表中的节点数字范围在 [1, 105]之间。
     * 1 <= Node.val <= 105
     */

    public static class Solution1 {

        /**
         * 哈希表: O(N), O(1)
         * Note: 我们用一个哈希表记录链表中每个元素值出现的次数，然后再遍历哈希表的值构造新的链表即可
         */
        public ListNode frequenciesOfElements(ListNode head) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (; head != null; head = head.next) {
                cnt.merge(head.val, 1, Integer::sum);
            }
            ListNode dummy = new ListNode();
            for (int val : cnt.values()) {
                dummy.next = new ListNode(val, dummy.next);
            }
            return dummy.next;
        }

    }

}
