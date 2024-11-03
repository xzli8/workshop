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
         PreComputation
         */
        public List<Integer> goodDaysToRobBank(int[] security, int time) {
            // PreCompute
            int n = security.length;
            int[] beforeNonIncreases = new int[n], afterNonDecreases = new int[n];
            for (int i = 1; i < n; i++) {
                if (security[i - 1] >= security[i]) {
                    beforeNonIncreases[i] = beforeNonIncreases[i - 1] + 1;
                } else {
                    beforeNonIncreases[i] = 0;
                }
            }
            for (int i = n - 2; i >= 0; i--) {
                if (security[i] <= security[i + 1]) {
                    afterNonDecreases[i] = afterNonDecreases[i + 1] + 1;
                } else {
                    afterNonDecreases[i] = 0;
                }
            }

            // compute
            List<Integer> res = new ArrayList<>();
            for (int i = time; i + time < n; i++) {
                if (beforeNonIncreases[i] >= time && afterNonDecreases[i] >= time) {
                    res.add(i);
                }
            }
            return res;
        }

    }

}
