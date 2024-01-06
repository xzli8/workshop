package data_structure_algorithm.leetcode;

public class Lintcode_1897meetingRoomIII {

    /**
     * 题目链接：https://www.lintcode.com/problem/1897/description
     */

    public static class Solution1 {

        public boolean[] meetingRoomIII(int[][] intervals, int rooms, int[][] ask) {
            // Write your code here.

            /**
             前缀和
             */

            // 将会议映射到时间轴上，times[i]表示时间点i对应的会议数量
            int maxTime = 50001;
            int[] times = new int[maxTime];
            int lastTime = 0;   // 最后一个有会议的时间点，优化后面的步骤（提前结束循环），也可以无
            for (int[] interval : intervals) {
                int i = interval[0];
                while (i < interval[1]) {
                    times[i++]++;
                }
                lastTime = Math.max(lastTime, interval[1]);
            }

            // 计算times的前缀和数组
            int[] sums = new int[maxTime];
            int sum = 0;
            for (int i = 0; i < lastTime; i++) {
                sum += (times[i] < rooms ? 0 : 1);  // 小于rooms，说明有空闲会议室
                sums[i] = sum;
            }

            // 遍历所有的ask，判断能否插入
            boolean[] res = new boolean[ask.length];
            for (int i = 0; i < ask.length; i++) {
                // shortcut，大于lastTime一定可以插入
                if (ask[i][0] >= lastTime) {
                    res[i] = true;
                } else {
                    res[i] = sums[ask[i][1] - 1] == sums[ask[i][0] - 1];
                }
            }
            return res;
        }

    }

}
