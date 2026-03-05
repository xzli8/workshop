package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _2100goodDaysToRobBank {

    public static class Solution1 {

        /**
         暴力解: TLE
         时间复杂度：O(N * K)
         空间复杂度：O(1)
         */
        // public List<Integer> goodDaysToRobBank(int[] security, int time) {
        //     List<Integer> res = new ArrayList<>();
        //     int n = security.length;
        //     for (int i = time; i + time < n; i++) {
        //         if (isGoodDay(security, i, time)) {
        //             res.add(i);
        //         }
        //     }
        //     return res;
        // }

        // private boolean isGoodDay(int[] security, int day, int time) {
        //     for (int i = day; i > day - time; i--) {
        //         if (security[i] > security[i - 1]) {
        //             return false;
        //         }
        //     }
        //     for (int i = day; i < day + time; i++) {
        //         if (security[i] > security[i + 1]) {
        //             return false;
        //         }
        //     }
        //     return true;
        // }

        /**
         DP: O(N), O(N)
         */
        public List<Integer> goodDaysToRobBank(int[] security, int time) {
            int n = security.length;
            int[] left = new int[n], right = new int[n];
            for (int i = 1; i < n; i++) {
                left[i] = security[i] <= security[i - 1] ? left[i - 1] + 1 : 0;
            }
            for (int i = n - 2; i >= 0; i--) {
                right[i] = security[i] <= security[i + 1] ? right[i + 1] + 1 : 0;
            }

            List<Integer> res = new ArrayList<>();
            for (int i = time; i < n - time; i++) {
                if (time <= Math.min(left[i], right[i])) {
                    res.add(i);
                }
            }
            return res;
        }

    }

}
