package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _1637maxWidthOfVerticalArea {

    public static class Solution1 {

        /**
         Sort
         TimeComplexity: O(NlogN)
         SapceComplexity: O(N)
         */
        public int maxWidthOfVerticalArea(int[][] points) {
            // sort by x coordinate
            Arrays.sort(points, (p1, p2) -> p1[0] - p2[0]);

            // find max
            int max = 0;
            for (int i = 1; i < points.length; i++) {
                max = Math.max(max, points[i][0] - points[i - 1][0]);
            }
            return max;
        }

    }

}
