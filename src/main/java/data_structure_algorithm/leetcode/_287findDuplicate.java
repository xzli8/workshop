package data_structure_algorithm.leetcode;

public class _287findDuplicate {

    public static class Solution1 {

         /**
          异或：（类似题："268-丢失的数字"）相同数字的异或结果为0，任何数字与0异或的结果为该数字本身。
          异或适用于"只有一个数字出现奇数次，其余数字都出现偶数次"的情况。
          wrong!!! input:[2, 2, 2, 2, 2]
          */
         public int findDuplicate(int[] nums) {
             int res = 0;
             for (int i = 0; i < nums.length; i++) {
                 res ^= i;
                 res ^= nums[i];
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         作差：（与"268-丢失的数字"一样）
         1.利用 nums 的数值范围为 [1,n][1, n][1,n]，我们可以先计算出 [1,n][1, n][1,n] 的总和 sum（利用等差数列求和公式）
         2.再计算 nums 的总和，两者之间的差值即是 nums中缺失的数字
         wrong!!! input:[2,2,2,2,2]
         */
         public int findDuplicate(int[] nums) {
             int n = nums.length;

             int sum1 = 0;
             for (int i = 0; i < n; i++) {
                 sum1 += nums[i];
             }

             int sum2 = 0;
             for (int i = 1; i < n; i++) {
                 sum2 += i;
             }
             return sum1 - sum2;
         }

    }



    public static class Solution3 {

        /**
         原地哈希（如果没有要求不修改原始数组）
         分析：将元素按照下标交换归位，如果有重复数字出现，返回
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int findDuplicate(int[] nums) {
             int n = nums.length;
             for (int i = 0; i < n; i++) {
                 while (nums[i] != i + 1) {
                     if (nums[i] == nums[nums[i] - 1]) return nums[i];
                     swap(nums, i, nums[i] - 1);
                 }
             }
             return -1;
         }

         private void swap(int[] nums, int i, int j) {
             int tmp = nums[i];
             nums[i] = nums[j];
             nums[j] = tmp;
         }

    }



    public static class Solution4 {

        /**
         类似：环形链表
         思路：建立从下标i到nums[i]的映射
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findDuplicate(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);

            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }

    }



    public static class Solution5 {

        /**
         二分查找
         分析：
         1.将n个整数放在长度为n+1的数组中，至少有一个数是重复的（抽屉原理 or 鸽巢理论）
         2.二分区间，重复元素要么在[left, mid]中，要么在[mid, right]中。
         3.统计nums中小于等于mid元素的数量count，若count > mid，根据抽屉原理，重复元素一定在[left, mid]中

         时间复杂度：O(NlogN)，用时间换空间
         空间复杂度：O(1)
         */
        public int findDuplicate(int[] nums) {

            // 先将nums中所有元素都减1，与下标一一对应起来
            for (int num : nums) {
                num--;
            }

            // 二分查找
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int mid = left + ((right - left) >> 1);

                // 统计nums中小于等于mid的元素个数count
                int count = 0;
                for (int num : nums) {
                    if (num <= mid) {
                        count++;
                    }
                }

                // 若count > mid，根据抽屉原理，重复元素一定在[left, mid]中
                if (count > mid) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            // 将nums中所有元素都加1，还原数组
            for (int num : nums) {
                num++;
            }
            return left;
        }

    }


}
