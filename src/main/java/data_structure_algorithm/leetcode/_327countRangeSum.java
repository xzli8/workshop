package data_structure_algorithm.leetcode;

public class _327countRangeSum {

    public static class Solution1 {

        /**
         前缀和 + 归并排序：在归并排序的过程中计算符合条件的区间数
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public int countRangeSum(int[] nums, int lower, int upper) {
            // 计算前缀和数组
            int n = nums.length;
            long[] preSums = new long[n + 1];
            for (int i = 1; i <= n; i++) preSums[i] = preSums[i - 1] + nums[i - 1];

            // 归并排序
            return mergeSort(preSums, new long[n + 1], 0, n, lower, upper);
        }

        private int mergeSort(long[] nums, long[] tmp, int left, int right, int lower, int upper) {
            if (left == right) return 0;
            int mid = left + ((right - left) >> 1);
            int res = mergeSort(nums, tmp, left, mid, lower, upper) + mergeSort(nums, tmp, mid + 1, right, lower, upper);

            // 计算符合条件的区间数(这里由于nums[i]是递增的，所以l,r只能向右移动)
            int l = mid + 1, r = mid + 1;
            for (int i = left; i <= mid; i++) {
                while (l <= right && nums[l] - nums[i] < lower) l++;
                while (r <= right && nums[r] - nums[i] <= upper) r++;
                res += r - l;
            }

            // 合并两个有序数组
            for (int i = left; i <= right; i++) tmp[i] = nums[i];
            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) nums[k] = tmp[j++];
                else if (j == right + 1) nums[k] = tmp[i++];
                else if (tmp[i] < tmp[j]) nums[k] = tmp[i++];
                else nums[k] = tmp[j++];
            }
            return res;
        }

    }

}
