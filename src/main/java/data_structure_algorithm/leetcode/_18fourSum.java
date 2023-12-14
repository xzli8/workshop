package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18fourSum {

    public static class Solution1 {

        /**
         排序 + 枚举 + 双指针：枚举前两个数，然后用双指针寻找后两个数
         时间复杂度：O(n^3)
         空间复杂度：O(logn)，排序空间
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int n = nums.length;
            if (n < 4) {
                return res;
            }

            // 排序
            Arrays.sort(nums);

            // 枚举第一个数
            for (int i = 0; i < n-3; i++) {
                // 去重
                if (i > 0 && nums[i] == nums[i-1]) {
                    continue;
                }

                // 剪枝（可有可无，加上可以提高效率）
                if ((long) nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target) {
                    break;
                }
                if ((long) nums[i] + nums[n-3] + nums[n-2] + nums[n-1] < target) {
                    continue;
                }

                // 枚举第二个数
                for (int j = i+1; j < n-2; j++) {
                    // 去重
                    if (j > i+1 && nums[j] == nums[j-1]) {
                        continue;
                    }

                    // 剪枝（可有可无，加上可以提高效率）
                    if ((long) nums[i] + nums[j] + nums[j+1] + nums[j+2] > target) {
                        break;
                    }
                    if ((long) nums[i] + nums[j] + nums[n-2] + nums[n-1] < target) {
                        continue;
                    }

                    // 双指针寻找后两个数
                    int left = j+1, right = n - 1;
                    while (left < right) {
                        long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                        if (sum == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            // 去重
                            while (left < right && nums[left] == nums[left+1]) {
                                left++;
                            }
                            left++;
                            while (left < right && nums[right] == nums[right-1]) {
                                right--;
                            }
                            right--;
                        } else if (sum < target) {
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
