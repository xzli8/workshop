package data_structure_algorithm.leetcode;

public class _162findPeakElement {

    public static class Solution1 {

        /**
         二分查找
         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
        public int findPeakElement(int[] nums) {
            int n = nums.length, left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if ((mid == 0 || nums[mid - 1] < nums[mid]) && (mid == n - 1 || nums[mid + 1] < nums[mid])) return mid;
                else if (mid == 0 || nums[mid - 1] < nums[mid]) left = mid + 1;
                else right = mid - 1;
            }
            return -1;
        }

    }



    public static class Solution2 {

        /**
         二分查找
         1.如果nums[i] < nums[i+1]，峰值一定在i之后（nums[i+2]有两种可能，一种是小于nums[i+1]，
         那么nums[i+1]就是峰值；如果大于nums[i+1]，继续后推，因为nums[n]=-∞，所以nums[n-1]是峰值）
         2.如果nums[i] > nums[i+1]，峰值一定在i之前（同理）

         时间复杂度：O(logn)
         空间复杂度：O(1)
         */
         public int findPeakElement(int[] nums) {
             int low = 0, high = nums.length - 1;
             while (low <= high) {
                 int mid = low + ((high - low) >> 1);
                 if (nums[mid] > get(nums, mid-1) && nums[mid] > get(nums, mid+1)) {
                     return mid;
                 } else if (nums[mid] < get(nums, mid+1)) {
                     low = mid + 1;
                 } else {
                     high = mid - 1;
                 }
             }
             return -1;
         }

         private long get(int[] nums, int index) {
             if (index < 0 || index >= nums.length) {
                 return Integer.MIN_VALUE - (long) 1;    // 这里一定要强转一下类型，要不然会自动转型为int，溢出
             }
             return nums[index];
         }

    }

}
