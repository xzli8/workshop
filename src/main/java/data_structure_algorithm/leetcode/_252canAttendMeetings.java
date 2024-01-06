package data_structure_algorithm.leetcode;

import java.util.Collections;
import java.util.List;

public class _252canAttendMeetings {


    /**
     *  题目链接：
     *      Leetcode252.会议室I：https://www.lintcode.com/problem/920/
     *      Leetcode253.会议室II：https://www.lintcode.com/problem/919/
     *      Lintcode1897.会议室III：https://www.lintcode.com/problem/1897
     */



    /**
     * Definition of Interval
     */
    public class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }



    public static class Solution1 {

        /**
         贪心
         时间复杂度：O(NlogN)，排序O(NlogN) + 遍历O(N)
         空间复杂度：O(logN)，排序O(logN)
         */
        public boolean canAttendMeetings(List<Interval> intervals) {
            if (intervals == null || intervals.size() < 2) {
                return true;
            }

            // 按照开始时间排序
            Collections.sort(intervals, (i1, i2) -> (i1.start - i2.start));

            // 判断当前区间的开始时间是否小于前一个区间的结束时间
            for (int i = 1; i < intervals.size(); i++) {
                if (intervals.get(i).start < intervals.get(i-1).end) {
                    return false;
                }
            }
            return true;
        }

    }



}
