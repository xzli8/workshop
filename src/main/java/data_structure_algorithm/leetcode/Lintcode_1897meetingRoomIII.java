package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class Lintcode_1897meetingRoomIII {


    /**
     *  题目链接：
     *      Leetcode252.会议室I：https://www.lintcode.com/problem/920/
     *      Leetcode253.会议室II：https://www.lintcode.com/problem/919/
     *      Lintcode1897.会议室III：https://www.lintcode.com/problem/1897
     */

    public static class Solution1 {

        /**
         前缀和
         分析：要判断某个区间能否插入，就需要判断该区间对应的时间段是否有足够空闲会议室。
         又因为待插入的区间互相独立，所以只要判断待插入区间对应的时间段是否有1间空间会议室。
         可以先计算出时间轴上空闲会议室的前缀和数组，然后用前缀和数组判断区间是否有空闲会议室。

         时间复杂度：O(intervals + times + asks)
         空间复杂度：O(times)
         */
        public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
            // 将当前所有会议投影到时间轴上
            int maxTime = 50001;
            int[] counts = new int[maxTime];    // counts[i]表示在时间点i正在进行的会议数量

            // Solution1: 遍历区间元素更新
            // for (int[] interval : intervals) {
            //     // 区间一定不能是"前闭后闭"或"前开后开"（当前写法），要是"前闭后开"或者"前开后闭"（后面写法）
            //     // for (int i = interval[0]; i < interval[1]; i++) {
            //     //     counts[i]++;
            //     // }

            //     // 当是"前开后闭"时，不需要减1(当前写法)；当是"前闭后开"时，后面计算ask是需要减1
            //     for (int i = interval[0] + 1; i <= interval[1]; i++) {
            //         counts[i]++;
            //     }
            // }

            // Solution2(prefer): 差分数组 -> 区间更新
            Difference diff = new Difference(counts);
            for (int[] interval : intervals) {
                // 前闭后开
                // diff.update(interval[0], interval[1] - 1 , 1);

                // 前开后闭
                diff.update(interval[0] + 1, interval[1], 1);
            }
            counts = diff.result();

            // 遍历时间轴，计算当前时间至少有1间空闲会议室的前缀和数组(这里每个询问互相独立，所以可以这样做。但如果需要同时插入多个会议就不行，需要考虑插入会议的互相影响)
            int[] preSum = new int[maxTime];
            int sum = 0;
            for (int i = 0; i < maxTime; i++) {
                sum += (counts[i] < rooms ? 0 : 1);
                preSum[i] = sum;
            }

            // 遍历待插入区间，用前缀和数组判断能否插入
            boolean[] res = new boolean[ask.length];
            for (int i = 0; i < ask.length; i++) {
                // 前闭后开
                // res[i] = preSum[ask[i][1] - 1] == preSum[ask[i][0] - 1];

                // 前开后闭
                res[i] = preSum[ask[i][1]] == preSum[ask[i][0]];
            }
            return res;
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


        @Test
        public void test() {
            int[][] intervals = {{1,3},{4,6},{6,8},{9,11},{6,9},{1,3},{4,10}};
            int rooms = 3;
            int[][] ask = {{1, 9}};
            System.out.println(Arrays.toString(meetingRoomIII(intervals, rooms, ask)));
        }

    }

}
