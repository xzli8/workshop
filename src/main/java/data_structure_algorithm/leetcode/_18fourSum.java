package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18fourSum {

    public static class Solution1 {

        /**
         排序 + 枚举 + 双指针：枚举前两个数，然后用双指针寻找后两个数
         时间复杂度：O(n^3)
         空间复杂度：O(1)
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



    public static class Solution2 {

        /**
         双指针：(类似题："15.三数之和")
         时间复杂度：O(N^3)，排序O(NlogN) + 双指针O(N^3)
         空间复杂度：O(1)
         */
        public List<List<Integer>> fourSum(int[] nums, int target) {
            // 排序
            Arrays.sort(nums);

            // 递归
            return nSum(nums, 4, target, 0);
        }

        // 求nSum，target定义为long防止越界
        private List<List<Integer>> nSum(int[] nums, int n, long target, int start) {

            List<List<Integer>> res = new ArrayList<>();

            // base case
            if (n == 2) {
                int left = start, right = nums.length - 1;
                while (left < right) {
                    long sum = nums[left] + nums[right];
                    if (sum == target) {
                        // 找到一个解
                        List<Integer> sub = new ArrayList();
                        sub.add(nums[left]);
                        sub.add(nums[right]);
                        res.add(sub);

                        // 去重
                        while (left < right && nums[left + 1] == nums[left]) left++;
                        while (left < right && nums[right - 1] == nums[right]) right--;

                        // 移动指针
                        left++;
                        right--;
                    }
                    else if (sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
            // 递归
            else {
                // 枚举
                for (int i = start; i < nums.length; i++) {
                    // 去重
                    if (i > start && nums[i] == nums[i - 1]) continue;

                    // 递归下一层
                    List<List<Integer>> subs = nSum(nums, n - 1, target - nums[i], i + 1);

                    // 返回本层
                    for (List<Integer> sub : subs) {
                        sub.add(nums[i]);
                        res.add(sub);
                    }
                }
            }
            return res;
        }

    }

}
