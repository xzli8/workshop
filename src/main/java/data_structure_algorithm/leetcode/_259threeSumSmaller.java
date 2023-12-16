package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _259threeSumSmaller {

    /**
     * 题目描述：给定一个长度为n的整数数组和一个目标值target，寻找能够使条件nums[i] + nums[j] + nums[k] < target成立的三元组i, j, k个数(0 <= i < j < k < n).
     * 示例：
     *      输入：nums = [-2, 0, 1, 3], target = 2
     *      输出：2
     *      解释：因为一共有两个三元组满足累加和小于2：[-2, 0, 1], [-2, 0, 3]
     * 进阶：能否在O(N^2)的时间复杂度内解决?
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals(2, threeSumSmaller(new int[] {-2, 0, 1, 3}, 2));
        }

        /**
         *  暴力搜索：依次枚举三个数，计算三元组的和
         *      时间复杂度：O(N^3)
         *      空间复杂度：O(1)
         */
        public int threeSumSmaller(int[] nums, int target) {
            int n = nums.length, count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (nums[i] + nums[j] + nums[k] < target) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }

    }



    public static class Solution2 {

        @Test
        public void test() {
            Assert.assertEquals(2, threeSumSmaller(new int[] {-2, 0, 1, 3}, 2));
        }

        /**
         * 排序 + 双指针：(类似题："15.三数之和")
         *      时间复杂度：O(N^2)，排序O(NlogN) + 双指针O(N^2)
         *      空间复杂度：O(1)
         */
        public int threeSumSmaller(int[] nums, int target) {
            // 排序
            Arrays.sort(nums);

            // 双指针
            int n = nums.length, count = 0;
            for (int i = 0; i < n - 2; i++) {
                int left = i + 1, right = n - 1;
                while (left < right) {
                    if (nums[i] + nums[left] + nums[right] < target) {
                        // 对于nums[i] + nums[left]而言，从left+1到right都满足
                        count += right - left;
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return count;
        }

    }



}
