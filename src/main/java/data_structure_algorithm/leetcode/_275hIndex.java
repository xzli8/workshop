package data_structure_algorithm.leetcode;

public class _275hIndex {

    public static class Solution1 {

        /**
         二分查找：值域二分，找最后一个满足条件的值
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int hIndex(int[] citations) {
            int n = citations.length, left = 1, right = n;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                // 与“274.H指数”相比，这里因为citations升序，所以可以将O(N)的check改成O(1)
                if (citations[n - mid] >= mid) {
                    if (mid == n || citations[n - (mid + 1)] < (mid + 1)) return mid;
                    else left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return 0;
        }

    }

}
