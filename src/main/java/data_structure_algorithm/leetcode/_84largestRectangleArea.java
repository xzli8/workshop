package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _84largestRectangleArea {

    public static class Solution1 {

        /**
         单调栈
         思路：对于位置i而言，向左找第一个小于heigth[i]的元素hegith[left]，向右找第一个小于height[i]的
         元素height[right]，area = height[i] * (right - left - 1)。可以通过暴力或者单调栈来解决

         时间复杂度：O(N)
         空间复杂度：O(N)

         暴力法：时间复杂度：O(N^2)，空间复杂度：O(1)
         */
        public int largestRectangleArea(int[] heights) {
            // 首尾各添加元素0，简化对于边界条件的处理
            int n = heights.length;
            int[] newHeights = new int[n+2];
            for (int i = 1; i <= n; i++) {
                newHeights[i] = heights[i-1];
            }

            // 从前往后遍历，维护单调(递增)栈，栈内记录下标（由下标可以得到高度，同时下标保留宽度信息）
            int maxArea = 0;
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < newHeights.length; i++) {
                while (!s.isEmpty() && newHeights[i] < newHeights[s.peek()]) {
                    int curIndex = s.pop();
                    maxArea = Math.max(maxArea, newHeights[curIndex] * (i - s.peek() - 1));
                    // i表示下标right, s.peek()表示下标left，这样就计算出了curIndex的最大面积
                }
                s.push(i);
            }
            return maxArea;
        }

    }

}
