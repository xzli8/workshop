package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.*;

public class _253minMeetingRooms {

    /**
     *  题目链接：
     *      Leetcode252.会议室I：https://www.lintcode.com/problem/920/
     *      Leetcode253.会议室II：https://www.lintcode.com/problem/919/
     *      Lintcode1897.会议室III：https://www.lintcode.com/problem/1897
     */


    public static class Solution1 {

        @Test
        public void test() {
//            System.out.println(minMeetingRooms(Arrays.asList(new Interval[] {new Interval(0, 30), new Interval(5, 10), new Interval(15, 20)})));
            System.out.println(minMeetingRooms(Arrays.asList(new Interval[] {new Interval(5, 8), new Interval(6, 8)})));
        }


        /**
         * Definition of Interval
         */
        public class Interval {

            public int start, end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

        }


        /**
         差分数组: O(N), O(N)
         */
        public int minMeetingRooms(List<Interval> intervals) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (Interval interval : intervals) {
                min = Math.min(min, interval.start);
                max = Math.max(max, interval.end);
            }

            int n = max - min + 1;
            int[] nums = new int[n];
            Difference diff = new Difference(nums);
            for (Interval interval : intervals) {
                // 看成"前闭后开"的区间(或者"前开后闭"也可以)
                // "-min"是做下标修正
                diff.update(interval.start - min, interval.end - 1 - min, 1);
            }
            nums = diff.result();

            int count = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                count = Math.max(count, nums[i]);
            }
            return count;
        }

        public class Difference {

            private int[] diff;

            public Difference(int[] nums) {
                diff = new int [nums.length];
                diff[0] = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    diff[i] = nums[i] - nums[i - 1];
                }
            }

            public void update(int start, int end, int num) {
                diff[start] += num;
                if (end + 1 < diff.length) diff[end + 1] -= num;
            }

            public int[] result() {
                int[] nums = new int[diff.length];
                nums[0] = diff[0];
                for (int i = 1; i < diff.length; i++) {
                    nums[i] = nums[i - 1] + diff[i];
                }
                return nums;
            }
        }

    }


    public static class Solution2 {

        /**
         * Definition of Interval
         */
        public class Interval {

            public int start, end;

            public Interval(int start, int end) {
                this.start = start;
                this.end = end;
            }

        }


        /**
         贪心 + 优先队列
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)
         */
        public int minMeetingRooms(List<Interval> intervals) {
            // 处理边界
            if(null == intervals || intervals.size() == 0) {
                return 0;
            }

            // 按照开始时间排序
            Collections.sort(intervals, Comparator.comparingInt(i -> i.start));

            // 优先队列（小顶堆）记录会议结束时间
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int count = 0;
            for (Interval interval : intervals) {
                // 在加入新的会议之前，先判断有没有会议结束
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
