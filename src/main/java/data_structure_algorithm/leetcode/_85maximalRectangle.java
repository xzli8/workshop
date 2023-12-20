package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _85maximalRectangle {

    public static class Solution1 {

        /**
         单调栈：求出每一层的heights，然后调用84题的函数求解
         时间复杂度：O(M * N)
         空间复杂度：O(N)
         */
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[] heights = new int[n];
            int maxArea = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == '1') {
                        heights[j] += 1;
                    } else {
                        heights[j] = 0;
                    }
                }
                maxArea = Math.max(maxArea, largestRectangleArea(heights));
            }
            return maxArea;
        }

        /**
         84-largestRectangleArea
         */
        public int largestRectangleArea(int[] heights) {
            // 首尾添加0，简化对于边界值的处理
            int n = heights.length;
            int[] newHeights = new int[n + 2];
            for (int i = 1; i <= n; i++) {
                newHeights[i] = heights[i - 1];
            }

            // 从前往后遍历，维护单调(递增)栈，栈内存下标
            int maxArea = 0;
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < newHeights.length; i++) {
                while (!s.isEmpty() && newHeights[i] < newHeights[s.peek()]) {
                    int curIndex = s.pop();
                    maxArea = Math.max(maxArea, (i - s.peek() - 1) * newHeights[curIndex]);
                }
                s.push(i);
            }
            return maxArea;
        }

    }

}
