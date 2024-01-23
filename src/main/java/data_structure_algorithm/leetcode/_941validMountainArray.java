package data_structure_algorithm.leetcode;

public class _941validMountainArray {

    public static class Solution1 {

        /**
         线性扫描
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public boolean validMountainArray(int[] arr) {
            int n = arr.length, i = 0;
            while (i + 1 < n && arr[i] < arr[i + 1]) i++;   // 递增扫描
            if (i == 0 || i == n - 1) return false;     // 最高点不能是开头或者结尾
            while (i + 1 < n && arr[i] > arr[i + 1]) i++;   // 递减扫描
            return i == n - 1;
        }

    }

}
