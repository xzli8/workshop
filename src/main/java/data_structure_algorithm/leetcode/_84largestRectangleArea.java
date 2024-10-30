package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class _84largestRectangleArea {

    /**
     思路：对于位置i而言，向左找第一个小于heigth[i]的元素hegith[left]，向右找第一个小于height[i]的
     元素height[right]，area = height[i] * (right - left - 1)。可以通过暴力或者单调栈来解决。
     暴力法：时间复杂度O(N^2)，空间复杂度O(1)
     单调栈：时间复杂度O(N)，空间复杂度O(N)
     类似题："907.子数组的最小值之和"
     */

    public static class Solution1 {

        /**
         单调栈-解法1
         思路：对于每个元素，利用单调栈计算其左右两边第一个小于该数的下标
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] left = new int[n], right = new int[n];

            // 对于每个元素，计算其左边第一个小于该数的元素下标，记入left[i]
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!s.isEmpty() && heights[i] <= heights[s.peek()]) s.pop();
                left[i] = s.isEmpty() ? -1 : s.peek();
                s.push(i);
            }

            // 对于每个元素，计算其右边第一个小于该数的元素下标，记入right[i]
            s.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && heights[i] <= heights[s.peek()]) s.pop();
                right[i] = s.isEmpty() ? n : s.peek();
                s.push(i);
            }

            // 遍历每个元素，计算其能勾勒出来的最大矩形面积
            int maxArea = 0;
            for (int i = 0; i < n; i++) {
                maxArea = Math.max(maxArea, heights[i] * (right[i] - left[i] - 1));
            }
            return maxArea;
        }

    }



    public static class Solution2 {

        /**
         单调栈-解法2：不显示记录left和right数组，而是在遍历过程中求maxArea，同时利用哨兵简化编程
         时间复杂度：O(N)
         空间复杂度：O(N)
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
                 // 当触发出栈条件时，意味着当前元素heights[i]就是右边第一个比出栈元素小的元素，那么就可以计算出栈元素对应的最大矩形面积
                 // https://leetcode.cn/problems/largest-rectangle-in-histogram/solutions/108083/84-by-ikaruga/
                 while (!s.isEmpty() && newHeights[i] < newHeights[s.peek()]) {
                     int curIndex = s.pop();
                     maxArea = Math.max(maxArea, newHeights[curIndex] * (i - s.peek() - 1));
                     // i表示下标right, s.peek()表示下标left(栈内元素单调递增，所以s.peek()就是左边界)，这样就计算出了curIndex的最大面积
                 }
                 s.push(i);
             }
             return maxArea;
         }

    }

}
