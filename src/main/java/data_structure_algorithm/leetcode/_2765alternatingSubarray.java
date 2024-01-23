package data_structure_algorithm.leetcode;

public class _2765alternatingSubarray {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int alternatingSubarray(int[] nums) {
            int n = nums.length, i = 0, maxLen = -1;
            while (i < n - 1) {
                // 不符合的直接跳过
                if (nums[i + 1] - nums[i] != 1) {
                    i++;
                    continue;
                }
                int start = i;  // 记录这一组开始的位置
                i += 2; // i和i+1已经满足要求，从i+2开始判断
                while (i < n && nums[i] == nums[i - 2]) i++;
                maxLen = Math.max(maxLen, i - start);
                i--;
            }
            return maxLen;
        }

    }

}
