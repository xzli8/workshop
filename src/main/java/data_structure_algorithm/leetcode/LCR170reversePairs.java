package data_structure_algorithm.leetcode;

public class LCR170reversePairs {

    /**
     归并排序(merge过程就是还原逆序对的过程)
     NOTE：
     对于逆序对的计数，可以采用全局变量的形式，也可以采用返回值的形式。
     计算逆序对时，可以计算i后面比i小的数，也可以计算j前面比j大的数，但不要同时都计算，那样就重复了。

     时间复杂度：O(NlogN)
     空间复杂度：O(N)
     */

    // 全局变量 + 计算i后面比i小的数
    public static class Solution1 {

        public int reversePairs(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            mergeSort(nums, tmp, 0, n - 1);
            return count;
        }

        private int count = 0;

        private void mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return;

            int mid = left + ((right - left) >> 1);
            mergeSort(nums, tmp, left, mid);
            mergeSort(nums, tmp, mid + 1, right);
            merge(nums, tmp, left, mid, right);
        }

        private void merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                    count += j - (mid + 1);
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                    count += j - (mid + 1);
                } else {
                    nums[k] = tmp[j++];
                }
            }
        }

    }

    // 返回值 + 计算i后面比i小的数
    public static class Solution2 {

        public int reversePairs(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            return mergeSort(nums, tmp, 0, n - 1);
        }

        private int mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return 0;

            int mid = left + ((right - left) >> 1);
            return mergeSort(nums, tmp, left, mid) + mergeSort(nums, tmp, mid + 1, right)
                    + merge(nums, tmp, left, mid, right);
        }

        private int merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1, count = 0;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                    count += j - (mid + 1);
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                    count += j - (mid + 1);
                } else {
                    nums[k] = tmp[j++];
                }
            }
            return count;
        }

    }

    // 全局变量 + 计算j前面比j大的数
    public static class Solution3 {

        public int reversePairs(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            mergeSort(nums, tmp, 0, n - 1);
            return count;
        }

        private int count = 0;

        private void mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return;

            int mid = left + ((right - left) >> 1);
            mergeSort(nums, tmp, left, mid);
            mergeSort(nums, tmp, mid + 1, right);
            merge(nums, tmp, left, mid, right);
        }

        private void merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                } else {
                    nums[k] = tmp[j++];
                    count += mid + 1 - i;
                }
            }
        }

    }


    // 返回值 + 计算j前面比j大的数
    public static class Solution4 {

        public int reversePairs(int[] nums) {
            int n = nums.length;
            int[] tmp = new int[n];
            return mergeSort(nums, tmp, 0, n - 1);
        }

        private int mergeSort(int[] nums, int[] tmp, int left, int right) {
            if (left >= right) return 0;

            int mid = left + ((right - left) >> 1);
            return mergeSort(nums, tmp, left, mid) + mergeSort(nums, tmp, mid + 1, right)
                    + merge(nums, tmp, left, mid, right);
        }

        private int merge(int[] nums, int[] tmp, int left, int mid, int right) {
            for (int k = left; k <= right; k++) {
                tmp[k] = nums[k];
            }

            int i = left, j = mid + 1, count = 0;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = tmp[j++];
                } else if (j == right + 1) {
                    nums[k] = tmp[i++];
                } else if (tmp[i] <= tmp[j]) {
                    nums[k] = tmp[i++];
                } else {
                    nums[k] = tmp[j++];
                    count += mid + 1 - i;
                }
            }
            return count;
        }

    }


}
