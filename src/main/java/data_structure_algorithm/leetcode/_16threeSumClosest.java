package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _16threeSumClosest {

    public static class Solution1 {

        /**
         双指针：(类似题："15.三数之和")
         时间复杂度：O(N^2)
         空间复杂度：O(1)
         */
        public int threeSumClosest(int[] nums, int target) {
            // 排序
            Arrays.sort(nums);

            int n = nums.length, res = 0, minError = Integer.MAX_VALUE;

            // 枚举第一个数
            for (int i = 0; i < n-2; i++) {
                // 去重（可有可无，加速查找）
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }

                // 双指针寻找另外两个数
                int left = i+1, right = n-1;
                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    // 如果和为target直接返回
                    if (sum == target) {
                        return sum;
                    } else {
                        // 找误差绝对值最小的
                        int error = Math.abs(sum - target);
                        if (error < minError) {
                            minError = error;
                            res = sum;
                        }

                        if (sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
            return res;
        }

    }

}
