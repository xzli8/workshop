package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _164maximumGap {

    public static class Solution1 {

        /**
         RadixSort
         TimeComplexity: O(N)
         SpaceComplexity: O(N)
         */
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) return 0;
            long exp = 1;
            int[] buf = new int[n];
            int maxVal = Arrays.stream(nums).max().getAsInt();

            while (maxVal >= exp) {
                int[] cnt = new int[10];
                for (int i = 0; i < n; i++) {
                    int digit = (nums[i] / (int) exp) % 10;
                    cnt[digit]++;
                }
                for (int i = 1; i < 10; i++) {
                    cnt[i] += cnt[i - 1];
                }
                for (int i = n - 1; i >= 0; i--) {
                    int digit = (nums[i] / (int) exp) % 10;
                    buf[--cnt[digit]] = nums[i];
                }
                System.arraycopy(buf, 0, nums, 0, n);
                exp *= 10;
            }

            int res = 0;
            for (int i = 1; i < n; i++) {
                res = Math.max(res, nums[i] - nums[i - 1]);
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         计数排序：OOM
         时间复杂度：O(N)
         空间复杂度：O(N + M)，M为数据范围
         */
         public int maximumGap(int[] nums) {
             int n = nums.length;
             if (n < 2) return 0;

             // 计数
             int minNum = nums[0], maxNum = nums[0];
             for (int num : nums) {
                 minNum = Math.min(minNum, num);
                 maxNum = Math.max(maxNum, num);
             }
             int[] counts = new int[maxNum - minNum + 1];
             for (int num : nums) counts[num - minNum]++;
             for (int i = 1; i < counts.length; i++) counts[i] += counts[i - 1];

             // 排序
             int[] sortedNums = new int[n];
             for (int i = n - 1; i >= 0; i--) sortedNums[--counts[nums[i] - minNum]] = nums[i];

             // 找最大差值
             int maxDiff = 0;
             for (int i = 1; i < n; i++) maxDiff = Math.max(maxDiff, sortedNums[i] - sortedNums[i - 1]);
             return maxDiff;
         }

    }

}
