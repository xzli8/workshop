package data_structure_algorithm.leetcode;

public class _327countRangeSum {

    public static class Solution1 {

        /**
         前缀和 + 归并排序：O(NlogN), O(N)
         Note: 在归并排序的过程中计算符合条件的区间数
         Ref: https://leetcode.cn/problems/count-of-range-sum/solutions/476038/qu-jian-he-de-ge-shu-by-leetcode-solution/
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

            // 对于每个左边元素，枚举右边元素直到满足条件[lower, upper]，计算符合条件的区间数(因为前面已经排序了，所以这里nums[i]在左右两个区间中是分别递增的，因此l,r只能向右移动)
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
