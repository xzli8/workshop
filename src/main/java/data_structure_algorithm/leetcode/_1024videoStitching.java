package data_structure_algorithm.leetcode;

import org.junit.Test;

public class _1024videoStitching {

    public static class Solution1 {

        public int videoStitching(int[][] clips, int time) {
            int maxTime = 100, curTime = 1, count = 0;
            while (curTime <= maxTime) {
                int max = -1;
                for (int[] clip : clips) {
                    if (clip[0] <= curTime && curTime <= clip[1] && clip[1] > max) {
                        max = clip[1];
                    }
                }
                if (max == -1) return -1;
                count++;
                curTime = max + 1;
            }
            return count;
        }


        @Test
        public void test() {
            int[][] clips = new int[][] {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
            int time = 10;
            System.out.println(videoStitching(clips, time));
        }


    }

}
