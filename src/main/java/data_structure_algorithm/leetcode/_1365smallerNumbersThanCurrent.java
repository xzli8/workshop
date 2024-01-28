package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1365smallerNumbersThanCurrent {

    public static class Solution1 {

        /**
         暴力枚举
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int[] smallerNumbersThanCurrent(int[] nums) {
             int n = nums.length;
             int[] res = new int[n];
             for (int i = 0; i < n; i++) {
                 int cnt = 0;
                 for (int j = 0; j < n; j++) {
                     if (nums[j] < nums[i]) cnt++;
                 }
                 res[i] = cnt;
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         排序
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
         public int[] smallerNumbersThanCurrent(int[] nums) {
             // 初始化pairs数组
             int n = nums.length;
             int[][] pairs = new int[n][2];
             for (int i = 0; i < n; i++) {
                 pairs[i][0] = nums[i];
                 pairs[i][1] = i;
             }

             // 根据值对paris数组排序
             Arrays.sort(pairs, (pair1, pair2) -> pair1[0] - pair2[0]);

             // 计算最终结果
             int[] res = new int[n];
             int prev = -1;
             for (int i = 0; i < n; i++) {
                 if (prev == -1 || pairs[i][0] != pairs[i - 1][0]) prev = i;
                 res[pairs[i][1]] = prev;
             }
             return res;
         }

    }



    public static class Solution3 {

        /**
         计数排序：数组元素范围在[0, 100]
         时间复杂度：O(N + K)
         空间复杂度：O(K)
         */
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] cnt = new int[101];
            int n = nums.length;
            for (int i = 0; i < n; i++) cnt[nums[i]]++;
            for (int i = 1; i <= 100; i++) cnt[i] += cnt[i - 1];
            int[] res = new int[n];
            for (int i = 0; i < n; i++) {
                res[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
            }
            return res;
        }

    }

}
