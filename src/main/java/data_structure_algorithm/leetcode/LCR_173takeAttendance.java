package data_structure_algorithm.leetcode;

public class LCR_173takeAttendance {

    public static class Solution1 {

        /**
         二分
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int takeAttendance(int[] records) {
            int n = records.length, left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (records[mid] != mid && (mid == 0 || records[mid - 1] == mid - 1)) return mid;
                if (records[mid] == mid) left = mid + 1;
                else right = mid - 1;
            }
            return n;
        }

    }

}
