package data_structure_algorithm.leetcode;

public class _852peakIndexInMountainArray {

    public static class Solution1 {

        /**
         二分查找(相似题："1095.山脉数组中查找目标值")
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int peakIndexInMountainArray(int[] arr) {
            int n = arr.length, left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if ((mid == 0 || arr[mid - 1] < arr[mid]) && (mid == n - 1 || arr[mid + 1] < arr[mid])) return mid;
                else if (mid == 0 || arr[mid - 1] < arr[mid]) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

    }



    public static class Solution2 {

        /**
         二分查找(相似题："1095.山脉数组中查找目标值")
         分析：这种写法有局限性，当arr[i]的取值范围是int时就不好办了，除非get方法返回值为long，不然会溢出
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         public int peakIndexInMountainArray(int[] arr) {
             int low = 0, high = arr.length - 1;
             while (low <= high) {
                 int mid = low + ((high - low) >> 1);
                 if (arr[mid] > get(arr, mid-1) && arr[mid] > get(arr, mid+1)) {
                     return mid;
                 } else if (arr[mid] < get(arr, mid+1)) {
                     low = mid + 1;
                 } else {
                     high = mid - 1;
                 }
             }
             return -1;
         }

         private int get(int[] arr, int index) {
             if (index < 0 || index >= arr.length) {
                 return -1;  // 题目规定 0 <= arr[i] <= 10^6，所以这里返回-1即可
             }
             return arr[index];
         }

    }

}
