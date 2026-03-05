package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _255verifyPreorder {

    /**
     * Ref: https://leetcode.doocs.org/lc/255/
     *      https://www.cnblogs.com/grandyang/p/5327635.html
     */

    public static class Solution1 {

        /**
         * 栈: O(N), O(N)
         * Note: 前序遍历序列是[根/左/右]，
         */
        public boolean verifyPreorder(int[] preorder) {
            Deque<Integer> s = new ArrayDeque<>();
            int low = Integer.MIN_VALUE;
            for (int x : preorder) {
                // 当前值小于这个最小值low，返回 false
                if (x < low) return false;
                // 遇到的数字比栈顶元素大，那么就是右边的值了，需要找到是哪个节点的右子树
                // 更新low值并删掉栈顶元素，然后继续和下一个栈顶元素比较，如果还是大于，则继续更新low值和删掉栈顶，直到栈为空或者当前栈顶元素大于当前值停止，压入当前值
                while (!s.isEmpty() && s.peek() < x) {
                    low = s.poll();
                }
                // 对于根节点，将其压入栈中，然后往后遍历，如果遇到的数字比栈顶元素小，说明是其左子树的点，继续压入栈中
                s.push(x);
            }
            return true;
        }

    }

}
