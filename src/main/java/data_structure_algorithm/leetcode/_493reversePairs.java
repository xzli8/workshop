package data_structure_algorithm.leetcode;

public class _493reversePairs {

    public static class Solution1 {

        /**
         分治(归并排序)：在两个有序数组中，翻转对的数量等于两个数组中翻转对的数量和左右端点分别位于两个子数组的翻转对数目
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public int reversePairs(int[] nums) {
            int n = nums.length;
            return mergeSort(nums, new int[n], 0, n - 1);
        }

        private int mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left == right) return 0;
            int mid = left + ((right - left) >> 1);
            int res = mergeSort(nums, tmp, left, mid) + mergeSort(nums, tmp, mid + 1, right);

            // 计算左右子端点分别位于两个子数组中的翻转对数目
            int l = left, r = mid + 1;
            while (l <= mid) {
                while (r <= right && (long) nums[l] > 2 * (long) nums[r]) r++;
                res += r - (mid + 1);
                l++;
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
