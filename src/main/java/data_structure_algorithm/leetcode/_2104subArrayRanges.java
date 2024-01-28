package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2104subArrayRanges {

    public static class Solution1 {

        /**
         暴力枚举
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public long subArrayRanges(int[] nums) {
             int n = nums.length;
             long sum = 0;
             for (int i = 0; i < n; i++) {
                 int min = nums[i], max = nums[i];
                 for (int j = i + 1; j < n; j++) {
                     min = Math.min(min, nums[j]);
                     max = Math.max(max, nums[j]);
                     sum += max - min;
                 }
             }
             return sum;
         }

    }



    public static class Solution2 {

        @Test
        public void test() {
            System.out.println(subArrayRanges(new int[] {1, 2, 3}));
        }


        /**
         单调栈(类似题："907.子数组的最小值之和")
         ref:https://leetcode.cn/problems/sum-of-subarray-ranges/solutions/1308898/gong-shui-san-xie-yi-ti-san-jie-qu-jian-wn84z/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            int[] minLeft = new int[n], minRight = new int[n], maxLeft = new int[n], maxRight = new int[n];

            // 计算最小值区间
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!s.isEmpty() && nums[i] < nums[s.peek()]) s.pop();
                minLeft[i] = s.isEmpty() ? -1 : s.peek();
                s.push(i);
            }
            s.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && nums[i] <= nums[s.peek()]) s.pop();
                minRight[i] = s.isEmpty() ? n : s.peek();
                s.push(i);
            }

            // 计算最大值区间
            s.clear();
            for (int i = 0; i < n; i++) {
                while(!s.isEmpty() && nums[i] >= nums[s.peek()]) s.pop();
                maxLeft[i] = s.isEmpty() ? -1 : s.peek();
                s.push(i);
            }
            s.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!s.isEmpty() && nums[i] > nums[s.peek()]) s.pop();
                maxRight[i] = s.isEmpty() ? n : s.peek();
                s.push(i);
            }

            // 计算每个元素的贡献
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += ((long) (i - maxLeft[i]) * (maxRight[i] - i) - (long) (i - minLeft[i]) * (minRight[i] - i)) * nums[i];
            }
            return sum;
        }

    }



    public static class Solution3 {

        @Test
        public void test() {
            System.out.println(subArrayRanges(new int[] {1, 2, 3}));
        }

        public long subArrayRanges(int[] nums) {
            int n = nums.length;
            int[] minLeft = new int[n], minRight = new int[n], maxLeft = new int[n], maxRight = new int[n];

            // 计算最小值/最大值的左边界
            Deque<Integer> minStack = new ArrayDeque<>(), maxStack = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!minStack.isEmpty() && nums[i] < nums[minStack.peek()]) minStack.pop();
                minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
                minStack.push(i);

                while (!maxStack.isEmpty() && nums[i] >= nums[maxStack.peek()]) maxStack.pop();
                maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
                maxStack.push(i);
            }

            // 计算最小值/最大值的右边界
            minStack.clear(); maxStack.clear();
            for (int i = n - 1; i >= 0; i--) {
                while (!minStack.isEmpty() && nums[i] <= nums[minStack.peek()]) minStack.pop();
                minRight[i] = minStack.isEmpty() ? n : minStack.peek();
                minStack.push(i);

                while (!maxStack.isEmpty() && nums[i] > nums[maxStack.peek()]) maxStack.pop();
                maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
                maxStack.push(i);
            }

            // 计算最终结果
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += ((long) (maxRight[i] - i) * (i - maxLeft[i]) - (long) (minRight[i] - i) * (i - minLeft[i])) * nums[i];
            }
            return sum;
        }

    }



}
