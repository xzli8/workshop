package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _698canPartitionKSubsets {

    public static class Solution1 {

        /**
         回溯：(站在球的视角)每个球可以选择放入k个桶的的任意一个，共k种选择，一共有n个球，所以总共有k^n种选择
         时间复杂度：O(K^N)
         空间复杂度：O(N)
         */
        public boolean canPartitionKSubsets(int[] nums, int k) {
            // 数字之和不为k的整数倍，肯定不能被划分成k个等和子集
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) return false;

            // 初始化桶
            int target = sum / k;   // 每个桶中元素的目标和
            int[] buckets = new int[k]; // 每个桶中元素的实际和

            // 降序排列(降低回溯的概率)
            Arrays.sort(nums);
            int left = 0, right = nums.length - 1;
            while (left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }

            // 回溯
            return backtrace(nums, 0, buckets, target);
        }

        // 枚举第i个球
        private boolean backtrace(int[] nums, int i, int[] buckets, int target) {
            // 已经枚举完所有球
            if (i == nums.length) {
                // 判断所有桶中的球是否符合要求
                for (int j = 0; j < buckets.length; j++) {
                    if (buckets[j] != target) return false;
                }
                return true;
            }

            // 每个球可以选择装入/不装入每个桶
            for (int j = 0; j < buckets.length; j++) {
                // 去重(如果当前桶和上一个桶内的元素相等，跳过，因为如果元素和相等，nums[i]选择上一个桶和当前桶一样)
                if (j > 0 && buckets[j] == buckets[j - 1]) continue;
                // 剪枝
                if (buckets[j] + nums[i] > target) continue;
                // 做选择：第i个球装入第j个桶
                buckets[j] += nums[i];
                // 处理下一个球。如果符合要求了提前返回
                if (backtrace(nums, i + 1, buckets, target)) return true;
                // 撤销选择：第i个球不装入第j个桶
                buckets[j] -= nums[i];
            }
            // 所有选择都结束了，仍然没找到满足条件的解，返回false
            return false;
        }

    }


    public static class Solution2 {

        /**
         回溯：(站在桶的视角)每个桶可以选择放入/不放入第i个球，一共有N个球，所以一个桶需要选择2^N次，共有k个桶，所以总次数2^N^K
         时间复杂度：O(2^N^K)
         空间复杂度：O(N)
         */
        public boolean canPartitionKSubsets(int[] nums, int k) {
            // 数字之和不为k的整数倍，肯定不能被划分成k个等和子集
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            if (sum % k != 0) return false;

            // 初始化桶
            int target = sum / k;   // 每个桶中元素的目标和
            int[] buckets = new int[k]; // 每个桶中元素的实际和

            // 回溯
            return backtrace(nums, buckets, k - 1, target, 0, new boolean[nums.length]);
        }

        private boolean backtrace(int[] nums, int[] buckets, int k, int target, int start, boolean[] used) {
            // k个桶都装满
            if (k == 0) return true;
            // 当前桶装满了，开始装下一个桶
            if (buckets[k] == target) {
                // 桶从下一个开始，球从第一个开始
                return backtrace(nums, buckets, k - 1, target, 0, used);
            }

            // 当前桶是否装入/不装入每个球
            for (int i = start; i < nums.length; i++) {
                // 球已经被使用过，跳过
                if (used[i]) continue;

                // 装入球后桶溢出，跳过
                if (buckets[k] + nums[i] > target) continue;

                // 装入第i个球
                used[i] = true;
                buckets[k] += nums[i];

                // 考虑下一个球
                if (backtrace(nums, buckets, k, target, i + 1, used)) return true;

                // 不装入第i个球
                buckets[k] -= nums[i];
                used[i] = false;
            }
            // 所有的球都不能让桶装满，返回false
            return false;
        }

    }



}
