package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _325maxSubArrayLen {

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals(4, maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
            Assert.assertEquals(2, maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
        }

        /**
         *  暴力搜索
         *      分析：因为有负数，所以不能用滑动窗口(无法满足单向性)，可以先尝试暴力求解。
         *      时间复杂度：O(N^3)
         *      空间复杂度：O(1)
         */
        public int maxSubArrayLen(int[] nums, int k) {
            int n = nums.length, maxLen = 0;

            // 枚举左右边界
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {

                    // 计算子数组和
                    int sum = 0;
                    for (int l = i; l <= j; l++) {
                        sum += nums[l];
                    }

                    // 更新最大子数组长度
                    if (sum == k && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                    }
                }
            }
            return maxLen;
        }

    }



    public static class Solution2 {

        @Test
        public void test() {
            Assert.assertEquals(4, maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
            Assert.assertEquals(2, maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
        }

        /**
         *  暴力搜索-改进：在枚举右边界的同时计算区间和，有点前缀和的意思
         *      分析：因为有负数，所以不能用滑动窗口(无法满足单向性)，可以先尝试暴力求解。
         *      时间复杂度：O(N^2)
         *      空间复杂度：O(1)
         */
        public int maxSubArrayLen(int[] nums, int k) {
            int n = nums.length, maxLen = 0;

            // 枚举左右边界
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = i; j < n; j++) {
                    sum += nums[j];

                    // 更新最大子数组长度
                    if (sum == k && j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                    }
                }
            }
            return maxLen;
        }

    }



    public static class Solution3 {

        @Test
        public void test() {
            Assert.assertEquals(4, maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
            Assert.assertEquals(2, maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
        }

        /**
         *  前缀和
         *      分析：因为有负数，所以不能用滑动窗口(无法满足单向性)，可以暴力求解，但时间复杂度较高。前缀和相当于用空间换时间，省去了遍历区间元素计算区间和的过程。
         *      时间复杂度：O(N^2)
         *      空间复杂度：O(N)
         */
        public int maxSubArrayLen(int[] nums, int k) {
            // 计算前缀和
            int n = nums.length;
            int[] preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }

            // 枚举左右边界，计算最大子数组长度
            int maxLen = 0;
            for (int i = 0; i <= n; i++) {
                for (int j = i; j <= n; j++) {
                    if (preSum[j] - preSum[i] == k && j - i > maxLen) {
                        maxLen = j - i;
                    }
                }
            }
            return maxLen;
        }

    }



    public static class Solution4 {

        @Test
        public void test() {
            Assert.assertEquals(4, maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
            Assert.assertEquals(2, maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
        }

        /**
         *  前缀和 + 哈希表：(类似题："1.两数之和"，求两数之和和用哈希表代替枚举，这里求前缀和的两数之差，也可以用哈希表代替枚举)
         *      分析：哈希表进一步用空间换时间
         *      时间复杂度：O(N)
         *      空间复杂度：O(N)
         */
        public int maxSubArrayLen(int[] nums, int k) {
            // 计算前缀和
            int n = nums.length;
            int[] preSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];

            }

            // 构建前缀和到下标的映射
            Map<Integer, Integer> preSum2Idx = new HashMap<>();
            for (int i = 0; i <= n; i++) {
                // 因为要计算最长子数组，所以这里当两个下标的前缀和相同时，记录前面的那个下标
                if (!preSum2Idx.containsKey(preSum[i])) {
                    preSum2Idx.put(preSum[i], i);
                }
            }

            // 从后往前遍历前缀和数组
            int maxLen = 0;
            for (int i = n; i >= 0; i--) {
                int remain = preSum[i] - k;
                if (preSum2Idx.containsKey(remain) && i - preSum2Idx.get(remain) > maxLen) {
                    maxLen = i - preSum2Idx.get(remain);
                }
            }
            return maxLen;
        }

    }



    public static class Solution5 {

        @Test
        public void test() {
            Assert.assertEquals(4, maxSubArrayLen(new int[] {1, -1, 5, -2, 3}, 3));
            Assert.assertEquals(2, maxSubArrayLen(new int[] {-2, -1, 2, 1}, 1));
        }

        /**
         *  前缀和 + 哈希表：(类似题："1.两数之和"，求两数之和和用哈希表代替枚举，这里求前缀和的两数之差，也可以用哈希表代替枚举)
         *      分析：哈希表进一步用空间换时间。一边遍历一边求前缀和，以及前缀和到下标的映射
         *      时间复杂度：O(N)
         *      空间复杂度：O(N)
         */
        public int maxSubArrayLen(int[] nums, int k) {
            int n = nums.length, preSum = 0, maxLen = 0;
            Map<Integer, Integer> preSum2Idx = new HashMap<>();
            preSum2Idx.put(0, -1);
            for (int i = 0; i < n; i++) {
                preSum += nums[i];
                int remain = preSum - k;
                if (preSum2Idx.containsKey(remain)) {
                    maxLen = Math.max(maxLen, i - preSum2Idx.get(remain));
                } else {
                    if (!preSum2Idx.containsKey(preSum)) {
                        preSum2Idx.put(preSum, i);
                    }
                }
            }
            return maxLen;
        }

    }





}
