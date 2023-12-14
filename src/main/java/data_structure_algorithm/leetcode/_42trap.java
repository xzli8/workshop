package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _42trap {

    public static class Solution1 {

        /**
         暴力法
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public int trap(int[] height) {
             int n = height.length;
             int sum = 0;

             // 两边的点不用考虑，肯定没有水
             for (int i = 1; i < n - 1; i++) {
                 // 找左边最大值
                 int leftMax = height[i-1];
                 for (int j = i - 2; j >= 0; j--) {
                     leftMax = Math.max(leftMax, height[j]);
                 }
                 // 找右边最大值
                 int rightMax = height[i+1];
                 for (int j = i + 2; j < n; j++) {
                     rightMax = Math.max(rightMax, height[j]);
                 }
                 // 计算水量
                 int minHeight = Math.min(leftMax, rightMax);
                 if (minHeight > height[i]) {
                     sum += minHeight - height[i];
                 }
             }
             return sum;
         }

    }



    public static class Solution2 {

        /**
         动态规划
         定义状态：leftMax[i]表示第i个元素左边的最大值，rightMax[i]表示第i个元素右边的最大值
         状态转移：leftMax[i] = Math.max(leftMax[i-1], height[i])，从左到右遍历
         rightMax[i] = Math.max(rightMax[i+1], height[i])，从右到左遍历

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int trap(int[] height) {
             int n = height.length;

             // 计算状态数组
             int[] leftMax = new int[n];
             leftMax[0] = 0;
             for (int i = 1; i < n; i++) {
                 leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
             }
             int[] rightMax = new int[n];
             rightMax[n-1] = 0;
             for (int i = n - 2; i >= 0; i--) {
                 rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
             }

             // 计算水量
             int sum = 0;
             for (int i = 1; i < n - 1; i++) {
                 int tmp = Math.min(leftMax[i], rightMax[i]) - height[i];
                 if (tmp > 0) {
                     sum += tmp;
                 }
             }
             return sum;
         }

    }




    public static class Solution3 {

        /**
         双指针：哪边高度小移动哪个
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int trap(int[] height) {
            int sum = 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                if (leftMax < rightMax) {
                    sum += leftMax - height[left++];
                } else {
                    sum += rightMax - height[right--];
                }
            }
            return sum;
        }

    }




    public static class Solution4 {


        /**
         单调(递减)栈
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int trap(int[] height) {
             int sum = 0;
             int n = height.length;
             Deque<Integer> s = new ArrayDeque<>();
             for (int i = 0; i < n; i++) {
                 while (!s.isEmpty() && height[i] > height[s.peek()]) {
                     int curIndex = s.pop();
                     if (s.isEmpty()) {
                         break;
                     }
                     int minHeight = Math.min(height[i], height[s.peek()]);
                     sum += (minHeight - height[curIndex])  * (i - s.peek() - 1);
                 }
                 s.push(i);
             }
             return sum;
         }

    }



}
