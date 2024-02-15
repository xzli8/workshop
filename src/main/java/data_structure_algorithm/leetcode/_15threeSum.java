package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15threeSum {

    public static class Solution1 {

        /**
         双指针：(类似题："167.两数之和II-输入有序数组")
         时间复杂度：O(N^2)，排序O(NlogN) + 双指针O(N^2)
         空间复杂度：O(1)
         */
        public List<List<Integer>> threeSum(int[] nums) {
            // 排序
            Arrays.sort(nums);

            List<List<Integer>> res = new ArrayList<>();

            // 固定一个元素
            for (int i = 0; i < nums.length; i++) {
                // 去重："if(nums[i + 1] == nums[i]) continue;"这样去重是错误的
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                // 双指针找另外两个元素
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int target = nums[i] + nums[left] + nums[right];
                    if (target == 0) {
                        // 找到一个解
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // 去重
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;

                        // 移动指针
                        left++;
                        right--;
                    }
                    else if (target < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         双指针(类似题："167.两数之和II-输入有序数组，18.四数之和")
         时间复杂度：O(N^2)，排序O(NlogN) + 双指针O(N^2)
         空间复杂度：O(1)
         */
        public List<List<Integer>> threeSum(int[] nums) {
            // 排序
            Arrays.sort(nums);

            // 递归
            return nSum(nums, 3, 0, 0);
        }

        // 双指针求nSum
        private List<List<Integer>> nSum(int[] nums, int n, int target, int start) {
            List<List<Integer>> res = new ArrayList<>();

            // base case
            if (n == 2) {
                int left = start, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum == target) {
                        // 找到一个解
                        List<Integer> sub = new ArrayList<>();
                        sub.add(nums[left]);
                        sub.add(nums[right]);
                        res.add(sub);

                        // res.add(Arrays.asList(nums[left], nums[right])); // 这样写得到的是不可变数组，后面不能再add元素了

                        // 去重
                        while (left < right && nums[left + 1] == nums[left]) left++;
                        while (left < right && nums[right - 1] == nums[right]) right--;

                        // 移动指针
                        left++;
                        right--;
                    }
                    else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            // 递归
            else {
                for (int i = start; i < nums.length; i++) {
                    // 去重
                    if (i > 0 && nums[i] == nums[i - 1]) continue;

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
