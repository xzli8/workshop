package data_structure_algorithm.leetcode;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class _253minMeetingRooms {

    /**
     * 题目链接：https://www.lintcode.com/problem/919/
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

        public int minMeetingRooms(List<Interval> intervals) {
            // 处理边界
            if(null == intervals || intervals.size() == 0) {
                return 0;
            }

            // 按照开始时间排序
            Collections.sort(intervals, (i1, i2) -> (i1.start - i2.start));

            // 优先队列（小顶堆）记录会议结束时间
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int count = 0;
            for (Interval interval : intervals) {
                while (!pq.isEmpty() && interval.start >= pq.peek()) {
                    pq.poll();
                }
                pq.offer(interval.end);
                count = Math.max(count, pq.size());
            }
            return count;
        }

    }

}
