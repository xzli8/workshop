package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _768maxChunksToSorted {

    public static class Solution1 {

        /**
         贪心 + 单调栈
         ref:https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/solutions/1741851/zui-duo-neng-wan-cheng-pai-xu-de-kuai-ii-w5c6/?envType=study-plan-v2&envId=selected-coding-interview
         https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/solutions/22785/zui-duo-neng-wan-cheng-pai-xu-de-kuai-ii-deng-jie-/?envType=study-plan-v2&envId=selected-coding-interview
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int maxChunksToSorted(int[] arr) {
            Deque<Integer> s = new ArrayDeque<>();  // 存储已分隔块的最大值，栈内元素单调递增
            for (int num : arr) {
                // 若当前元素大于等于最后一个块的最大值，那么当前元素可以单独作为一个块
                if (s.isEmpty() || num >= s.peek()) {
                    s.push(num);
                }
                // 若当前元素小于最后一个块的最大值，那么当前元素需要被融合进最后一个块中
                else {
                    // 不断往前面已分隔好的块中融入，直到当前元素大于等于某个块中元素的最大值为止
                    int max = s.pop();
                    while (!s.isEmpty() && s.peek() > num) s.pop();
                    s.push(max);
                }
            }
            return s.size();
        }

    }

}
