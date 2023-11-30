package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _57insert {

    public static class Solution1 {

        /**
         指针扫描：newInterval的两个端点将整个区间分为3段
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int n = intervals.length, i = 0;
            List<int[]> res = new ArrayList<>();

            // 左段
            while (i < n && intervals[i][1] < newInterval[0]) {
                res.add(intervals[i]);
                i++;
            }

            // 中段
            int left = newInterval[0], right = newInterval[1];
            while (i < n && intervals[i][0] <= newInterval[1]) {
                left = Math.min(left, intervals[i][0]);
                right = Math.max(right, intervals[i][1]);
                i++;
            }
            res.add(new int[] {left, right});

            // 右段
            while (i < n) {
                res.add(intervals[i]);
                i++;
            }

            return res.toArray(new int[res.size()][]);
        }

    }

}
