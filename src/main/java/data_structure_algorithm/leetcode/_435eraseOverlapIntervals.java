package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class _435eraseOverlapIntervals {

    public static class Solution1 {

        /**
         贪心(相当于会议室预定，需要找最先结束的会议)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int eraseOverlapIntervals(int[][] intervals) {
             // 将所有区间按照起点大小排序
             Arrays.sort(intervals, new Comparator<int[]>() {
                 public int compare(int[] interval1, int[] interval2) {
                     return interval1[0] - interval2[0];
                 }
             });

             int count = 0;  // 需要移除的区间数量
             int end = intervals[0][1];
             for (int i = 1; i < intervals.length; i++) {
                 // 重叠时，count++，并且将end更新为两重叠区间中较小的end，即将end较大的区间移除
                 if (intervals[i][0] < end) {
                     count++;
                     end = Math.min(end, intervals[i][1]);
                 } else {
                     end = intervals[i][1];
                 }
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         贪心(从区间终点考虑，找最多无重叠的区间)
         */
        public int eraseOverlapIntervals(int[][] intervals) {
            // 根据区间终点大小排序
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[1] - interval2[1];
                }
            });

            // 计算无重叠区间的数量
            int count = 1;
            int end = intervals[0][1];
            for (int i = 1; i < intervals.length; i++) {
                // 区间无重叠
                if (intervals[i][0] >= end) {
                    count++;
                    end = intervals[i][1];
                }
                // 这里没有更新end，相当于移除掉了end较大的区间
            }
            // 需要移除的区间数量
            return intervals.length - count;
        }

    }


}
