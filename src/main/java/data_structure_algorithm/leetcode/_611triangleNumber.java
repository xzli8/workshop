package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _611triangleNumber {

    public static class Solution1 {

        /**
         1.排序 + 双指针
         时间复杂度：O(N^2)
         空间复杂度：O(logN)
         */
        public int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length, res = 0;
            for (int i = n - 1; i >= 0; i--) {
                int left = 0, right = i - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > nums[i]) {
                        res += right - left;    // left 在 < right 区间变化都满足条件
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return res;
        }

    }

    public static class Solution2 {

        // 排序 + 枚举：O(N^3)
        public Integer triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int res = 0, n = nums.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] + nums[j] > nums[k]) {
                            res++;
                        } else {
                            break;
                        }
                    }
                }
            }
            return res;
        }

    }
}
