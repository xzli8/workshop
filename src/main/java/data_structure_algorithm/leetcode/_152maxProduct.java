package data_structure_algorithm.leetcode;

public class _152maxProduct {

    public static class Solution1 {

        /**
         动态规划
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int maxProduct(int[] nums) {
             int n = nums.length;

             // 定义状态：dpMax[i]表示以nums[i]结尾的子数组的最大乘积，dpMin[i]表示以nums[i]结尾的子数组的最小乘积
             int[] dpMax = new int[n], dpMin = new int[n];

             // 初始状态
             dpMax[0] = nums[0];
             dpMin[0] = nums[0];

             // 状态转移：最大值可以由前一个的最大值(当前元素为正数)、前一个的最小值(当前元素为负数)、当前元素转换来
             int res = dpMax[0];
             for (int i = 1; i < n; i++) {
                 dpMax[i] = Math.max(Math.max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]), nums[i]);
                 dpMin[i] = Math.min(Math.min(dpMin[i - 1] * nums[i], dpMax[i - 1] * nums[i]), nums[i]);
                 res = Math.max(res, dpMax[i]);
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         动态规划：优化空间复杂度到O(1)
         */
        public int maxProduct(int[] nums) {
            int n = nums.length;
            int max = nums[0], min = nums[0], res = nums[0];
            for (int i = 1; i < n; i++) {
                int maxNext = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
                int minNext = Math.min(Math.min(max * nums[i], min * nums[i]), nums[i]);
                max = maxNext;
                min = minNext;
                res = Math.max(res, max);
            }
            return res;
        }

    }



    public static class Solution3 {

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
