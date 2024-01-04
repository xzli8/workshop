package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class _1024videoStitching {

    public static class Solution1 {

        /**
         动态规划(类似题:“45.跳跃游戏II”)
         定义状态：dp[i]表示拼接[0, i]需要的最少次数
         状态转移：当clips[j][0] < i <= clips[j][1], dp[i] = Math.min(dp[i], dp[clips[j][0]] + 1)
         初始状态：dp[0] = 0

         时间复杂度：O(M * N)
         空间复杂度：O(M)
         */
        public int videoStitching(int[][] clips, int time) {
            // 定义状态
            int[] dp = new int[time + 1];

            // 初始状态
            Arrays.fill(dp, Integer.MAX_VALUE - 1); // 这里-1防止后面+1时溢出
            dp[0] = 0;

            // 状态转移
            for (int i = 1; i <= time; i++) {
                for (int[] clip : clips) {
                    if (clip[0] <= i && i <= clip[1]) {
                        dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                    }
                }
            }
            return dp[time] == Integer.MAX_VALUE - 1 ? -1 : dp[time];
        }

    }



    public static class Solution2 {

        /**
         贪心(类似题："45.跳跃游戏II")：先计算出每个区间能到达的最远位置
         时间复杂度：O(M + N)
         空间复杂度：O(M)
         */
        public int videoStitching(int[][] clips, int time) {
            // 计算每个区间能到达的最远位置
            int[] maxPositions = new int[time + 1];
            for (int[] clip : clips) {
                if (clip[0] <= time) {
                    maxPositions[clip[0]] = Math.max(maxPositions[clip[0]], clip[1]);
                }
            }

            // 遍历所有位置，每次跳到最远能到达的位置
            int steps = 0, maxPosition = 0, end = 0;
            for (int i = 0; i < time; i++) {
                // 找到能跳到更远的位置
                maxPosition = Math.max(maxPosition, maxPositions[i]);
                if (maxPosition == i) return -1;

                // 遇到边界时，更新边界，并且更新步数
                if (i == end) {
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }

    }



    public static class Solution3 {

        /**
         贪心：(另一种思路，用时间换空间)按照区间开始时间排序，每次选结束时间最大的区间作为下一次遍历的起点
         时间复杂度：O(N*M)，N表示区间数量，M表示时间数量
         空间复杂度：O(logN)，排序的空间复杂度
         */
         public int videoStitching(int[][] clips, int time) {
             // 按照区间开始时间排序
             Arrays.sort(clips, (c1, c2) -> c1[0] - c2[0]);

             // start > 0的肯定不能覆盖[0, time]
             if (clips[0][0] > 0) return -1;

             // 每次取能遍历到的区间中end最远的，作为下一次遍历的起始位置，即每次尽可能走得远
             int i = 0, start = 0, end = 0, count = 0;
             while (end < time) {
                 while (i < clips.length && clips[i][0] <= start) {
                     end = Math.max(end, clips[i++][1]);
                 }
                 if (end == start) return -1;    // end没有被更新意味着区间产生断层，无法全覆盖
                 start = end;
                 count++;
             }
             return count;
         }

    }



}
