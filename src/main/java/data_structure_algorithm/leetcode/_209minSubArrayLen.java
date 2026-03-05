package data_structure_algorithm.leetcode;

public class _209minSubArrayLen {

    public static class Solution1 {

        /**
         滑动窗口
         Note: nums中元素都是正数，满足滑动窗口的单向性。
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int minSubArrayLen(int target, int[] nums) {
            int n = nums.length, left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;
            while (right < n) {
                sum += nums[right++];
                while (sum >= target) {
                    minLen = Math.min(minLen, right - left);
                    sum -= nums[left++];
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }

    }


    public static class Solution2 {

        /**
         PreSum + BinarySearch: O(NlogN), O(N)
         */
        public int minSubArrayLen(int target, int[] nums) {
            // 因为nums都是正数，所以preSum单调递增
            int n = nums.length;
            long[] preSum = new long[n + 1];
            for (int i = 0; i < n; i++) {
                preSum[i + 1] = preSum[i] + nums[i];
            }

            // 在preSum数组上用二分查找找满足sum > target的最小长度子数组
            int minLen = n + 1;
            for (int i = 0; i <= n; i++) {
                int j = bsearch(preSum, preSum[i] + target);
                System.out.println(j);
                if (j > 0) minLen = Math.min(minLen, j - i);
            }
            return minLen == n + 1 ? 0 : minLen;
        }

        private int bsearch(long[] nums, long target) {
            int l = 0, r = nums.length - 1;
            while (l <= r) {
                int m = l + ((r - l) >> 1);
                if (nums[m] >= target) {
                    if (m == 0 || nums[m - 1] < target) {
                        return m;
                    }
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return -1;
        }

    }

}
