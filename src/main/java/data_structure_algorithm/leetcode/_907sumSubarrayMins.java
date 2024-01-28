package data_structure_algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

public class _907sumSubarrayMins {

    public static class Solution1 {

        /**
         暴力枚举(超时)
         时间复杂度：O(N^3)
         空间复杂度：O(1)
         */
         public int sumSubarrayMins(int[] arr) {
             int MOD = (int) 1e9 + 7, n = arr.length, sum = 0;
             for (int i = 0; i < n; i++) {
                 for (int j = i; j < n; j++) {
                     int min = arr[i];
                     for (int k = i + 1; k <= j; k++) {
                         min = Math.min(min, arr[k]);
                     }
                     sum = (sum + min) % MOD;
                 }
             }
             return sum;
         }

    }



    public static class Solution2 {


        /**
         暴力枚举(超时)
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
         public int sumSubarrayMins(int[] arr) {
             int MOD = (int) 1e9 + 7, n = arr.length, sum = 0;
             for (int i = 0; i < n; i++) {
                 int min = arr[i];
                 for (int j = i; j < n; j++) {
                     min = Math.min(min, arr[j]);
                     sum = (sum + min) % MOD;
                 }
             }
             return sum;
         }


    }



    public static class Solution3 {

        /**
         单调栈(类似题："84.柱状图中的最大矩形")
         思路：https://leetcode.cn/problems/sum-of-subarray-minimums/solutions/1164006/xiao-bai-lang-dong-hua-xiang-jie-bao-zhe-489q/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int sumSubarrayMins(int[] arr) {
            int MOD = (int) 1e9 + 7, n = arr.length;
            int[] left = new int[n], right = new int[n];

            // 对于每一个数，找其左边第一个小于该数的数，即确定该数辐射范围的左边界
            Deque<Integer> s = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                while (!s.isEmpty() && arr[i] < arr[s.peek()]) s.pop();
                left[i] = s.isEmpty() ? -1 : s.peek();
                s.push(i);
            }

            // 对于每一个数，找其右边第一个小于该数的数，即确定该数辐射范围的右边界
            s.clear();
            for (int i = n - 1; i >= 0; i--) {
                // 为什么这里是<=，而不是和上面一样是<？
                // 因为一个子数组里面可能有多个相同的最小值，为了不重复，那此时这个子数组只能让一个最小值做贡献。
                // 所以为了统一，可以让最小值里面的最左边的值来做贡献。(也可以让前面是<=，这里是<)
                while (!s.isEmpty() && arr[i] <= arr[s.peek()]) s.pop();
                right[i] = s.isEmpty() ? n : s.peek();
                s.push(i);
            }

            // 计算每个点的贡献度
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum = (sum + (long) (i - left[i]) * (right[i] - i) * arr[i]) % MOD;
            }
            return (int) sum;
        }

    }


}
