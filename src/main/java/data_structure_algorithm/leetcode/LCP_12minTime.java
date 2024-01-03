package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class LCP_12minTime {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(NlogM)
         空间复杂度：O(1)
         */
        public int minTime(int[] time, int m) {
            // 二分查找，首先确定二分的上线边界(不用特别精确)
            int left = 0, right = Arrays.stream(time).sum();
            while (left <= right) {
                int mid = ((right - left) >> 1) + left;
                if (count(time, mid) > m) {
                    left = mid + 1;
                } else {
                    if (mid == left || count(time, mid - 1) > m) {
                        return mid;
                    }
                    right = mid - 1;
                }
            }
            return -1;
        }

        private int count(int[] time, int mid) {
            int count = 1, sum = 0, max = 0;
            boolean help = false;
            for (int i = 0; i < time.length; i++) {
                max = Math.max(max, time[i]);
                if (sum + time[i] <= mid) {
                    sum += time[i];
                } else {
                    if (sum + time[i] - max <= mid && !help) {
                        help = true;
                        sum += time[i] - max;
                    } else {
                        count++;
                        sum = time[i];
                        max = time[i];
                        help = false;
                    }
                }
            }
            return count;
        }

    }

}
