package data_structure_algorithm.leetcode;

public class _167twoSum {

    public static class Solution1 {

        /**
         二分查找：固定一个数，用二分查找寻找另一个数
         时间复杂度：O(nlogn)
         空间复杂度：O(1)
         */
         public int[] twoSum(int[] numbers, int target) {
             int n = numbers.length;
             // 枚举第一个数，固定
             for (int i = 0; i < n; i++) {
                 // 为了避免重复，从后续区间中二分查找第二个数
                 int low = i+1, high = n-1;
                 while (low <= high) {
                     int mid = low + ((high - low) >> 1);
                     if (numbers[mid] == target - numbers[i]) {
                         return new int[] {i+1, mid+1};
                     } else if (numbers[mid] < target - numbers[i]) {
                         low = mid + 1;
                     } else {
                         high = mid - 1;
                     }
                 }
             }
             return new int[2];
         }

    }



    public static class Solution2 {

        /**
         双指针
         题解：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solutions/87919/yi-zhang-tu-gao-su-ni-on-de-shuang-zhi-zhen-jie-fa/
         时间复杂度：O(n)
         空间复杂度：O(1)
         */
        public int[] twoSum(int[] numbers, int target) {
            int left = 0, right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    return new int[]{left+1, right+1};
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            return new int[2];
        }

    }

}
