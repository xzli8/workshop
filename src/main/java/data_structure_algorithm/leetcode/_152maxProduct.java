package data_structure_algorithm.leetcode;

public class _152maxProduct {

    public static class Solution1 {

        /**
         动态规划：因为存在负数，所以可能让最大值变为最小值，最小值变为最大值
         定义状态：imax表示当前位置的最大值，imin表示当前位置的最小值
         状态转移：imax = max(imax, imax * nums[i]), imin = min(imin, imin * nums[i])
         时间复杂度：O(n)
         空间复杂度：O(1)
         */
        public int maxProduct(int[] nums) {
            int max = Integer.MIN_VALUE, imax = 1, imin = 1;
            for (int i = 0; i < nums.length; i++) {
                // 当遇到负数时，交换imax和imin
                if (nums[i] < 0) {
                    int tmp = imax;
                    imax = imin;
                    imin = tmp;
                }
                imax = Math.max(imax * nums[i], nums[i]);
                imin = Math.min(imin * nums[i], nums[i]);
                max = Math.max(max, imax);
            }
            return max;
        }

    }

}
