package data_structure_algorithm.leetcode;

public class _1482minDays {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(NlogM)，N为数据量，M为数据范围
         空间复杂度：O(1)
         */
        public int minDays(int[] bloomDay, int m, int k) {
            int n = bloomDay.length;
            if (n < m * k) return -1;

            int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
            for (int day : bloomDay) {
                left = Math.min(left, day);
                right = Math.max(right, day);
            }

            while (left <= right) {
                int mid = ((right - left) >> 1) + left;
                if (count(bloomDay, mid, k) < m) {
                    left = mid + 1;
                } else {
                    if (mid == left || count(bloomDay, mid - 1, k) < m) {
                        return mid;
                    }
                    right = mid - 1;
                }
            }
            return -1;
        }

        private int count(int[] bloomDay, int mid, int k) {
            int n = bloomDay.length, left = 0, right = 0, count = 0;
            while (right < n) {
                while (right < n && bloomDay[right] > mid) {
                    right++;
                }
                left = right;
                while (right < n && bloomDay[right] <= mid) {
                    right++;
                }
                count += (right - left) / k;
            }
            return count;
        }

    }

}
