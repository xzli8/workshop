package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class _452findMinArrowShots {

    public static class Solution1 {

        /**
         排序 + 贪心（类似435，找无重叠区间的数量，重叠的区间只需要一箭）
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)
         */
        public int findMinArrowShots(int[][] points) {
            // 按照区间终点排序
            Arrays.sort(points, new Comparator<int[]>() {
                public int compare(int[] p1, int[] p2) {
                    // return p1[1] - p2[1];    // 直接这样写会overflow
                    if (p1[1] > p2[1]) {
                        return 1;
                    } else if (p1[1] < p2[1]) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            // 遍历区间，找无重叠区间
            int count = 1, end = points[0][1];
            for (int i = 1; i < points.length; i++) {
                if (points[i][0] > end) {
                    count++;
                    end = points[i][1];
                }
            }
            return count;
        }

    }

}
