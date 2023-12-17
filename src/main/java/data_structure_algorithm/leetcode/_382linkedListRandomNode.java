package data_structure_algorithm.leetcode;

import data_structure_algorithm.domain.ListNode;

import java.util.Random;

public class _382linkedListRandomNode {

    /**
     * 参考题解：https://www.cnblogs.com/labuladong/p/17065673.html
     */

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(head);
     * int param_1 = obj.getRandom();
     */

    public class Solution {

        /**
         水塘抽样：顺序遍历，当遍历到第i个元素时，有1/i的概率选择当前元素，(1 - 1/i)的概率保持之前选择
         时间复杂度：初始化为O(1)，getRandom为O(N)
         空间复杂度：O(1)
         */
        private ListNode head;
        private Random random = new Random();

        public Solution(ListNode head) {
            this.head = head;
        }

        public int getRandom() {
            int res = 0, i = 0;
            for (ListNode p = head; p != null; p = p.next) {
                if (random.nextInt(++i) == 0) {
                    res = p.val;
                }
            }
            return res;
        }

        /**
         * 拓展：返回链表中k个随机节点的值
         */
        public int[] getRandom(int k) {
            // 前 k 个元素先默认选上
            int[] res = new int[k];
            ListNode p = head;
            for (int i = 0; i < k && p != null; i++) {
                res[i] = p.val;
                p = p.next;
            }

            // while 循环遍历链表
            int i = k;
            while (p != null) {
                i++;
                // 生成一个 [0, i) 之间的整数
                int j = random.nextInt(i);
                // 这个整数小于 k 的概率就是 k/i
                if (j < k) {
                    res[j] = p.val;
                }
                p = p.next;
            }
            return res;
        }

    }

}
