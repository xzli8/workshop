package data_structure_algorithm.leetcode;

public class _75sortColors {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void sortColors(int[] nums) {
            int n = nums.length;

            // 第一趟扫描，将红色和非红色分开
            int left = 0, right = n - 1;
            while (left < right) {
                while (nums[left] == 0 && left < right) {
                    left++;
                }
                while (nums[right] != 0 && left < right) {
                    right--;
                }
                swap(nums, left, right);
            }

            // 第二趟扫描，将白色和蓝色分开
            right = n - 1;
            while (left < right) {
                while (nums[left] == 1 && left < right) {
                    left++;
                }
                while (nums[right] != 1 && left < right) {
                    right--;
                }
                swap(nums, left, right);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }


        // 三指针：每个元素只扫描一遍
        public void sortColorsII(int[] nums) {
            int low = 0, mid = 0, high = nums.length - 1;
            while (mid <= high) {
                if (nums[mid] == 0)      swap(nums, low++, mid++);
                else if (nums[mid] == 1) mid++;
                else                     swap(nums, mid, high--); // 注意:这里 mid 不动
            }
        }

    }


    public static class Solution2 {

        /**
         * 扩展到k种颜色: 1.递归; 2.计数排序
         */
        public void sortColors(int[] nums) {
            // 颜色范围 [0, k-1]，这里 k = 3
            kColors(nums, 3, 0, 0, nums.length - 1);
        }

        // 将 nums[start..end] 排序，区间内只含颜色 [color, color + k - 1]，共 k 种颜色
        private void kColors(int[] nums, int k, int color, int start, int end) {
            // base case：颜色只剩 1 种 或 区间长度 <= 1，无需处理
            if (k <= 1 || start >= end) return;

            // 一趟双指针分区：把颜色 color 甩到区间前面，其余（必然 > color）甩到后面
            int left = start, right = end;
            while (left < right) {
                while (left < right && nums[left] == color) left++;
                while (left < right && nums[right] != color) right--;
                swap(nums, left, right);
            }

            // 计算 color 区间的右边界（第一个非 color 的下标）
            // 注意 left==right 处的元素可能是 color、也可能不是，需要判断
            int boundary = (nums[left] == color) ? left + 1 : left;

            // 递归处理剩下的 k-1 种颜色
            kColors(nums, k - 1, color + 1, boundary, end);
        }

        private void swap(int[] nums, int i, int j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

    }


    public static class Solution3 {

        /**
         * 计数排序：O(N + K), O(K)
         */
        public void sortColors(int[] nums) {
            sortKColors(nums, 3);
        }

        private void sortKColors(int[] nums, int k) {
            // 1. 计数：统计每种颜色出现次数
            int[] count = new int[k];
            for (int x : nums) {
                count[x]++;
            }
            // 2. 按颜色顺序覆盖写回
            int idx = 0;
            for (int color = 0; color < k; color++) {
                while (count[color]-- > 0) {
                    nums[idx++] = color;
                }
            }
        }

    }

}
