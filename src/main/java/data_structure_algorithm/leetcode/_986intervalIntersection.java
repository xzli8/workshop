package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _986intervalIntersection {

    public static class Solution1 {

        /**
         双指针 + 贪心(类似题："56.合并区间", "88.合并两个有序数组")
         时间复杂度：O(M + N)
         空间复杂度：O(1)
         */
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            List<int[]> res = new ArrayList<>();
            int i = 0, j = 0;
            while (i < firstList.length && j < secondList.length) {
                if (firstList[i][1] < secondList[j][0]) {
                    i++;
                } else if (secondList[j][1] < firstList[i][0]) {
                    j++;
                } else {
                    res.add(new int[] {Math.max(firstList[i][0], secondList[j][0]), Math.min(firstList[i][1], secondList[j][1])});
                    if (firstList[i][1] < secondList[j][1]) {
                        i++;
                    }
                    else if (secondList[j][1] < firstList[i][1]) {
                        j++;
                    }
                    else {
                        i++;
                        j++;
                    }
                }
            }
            return res.toArray(new int[res.size()][]);
        }

    }



    public static class Solution2 {

        /**
         双指针：更简洁的写法，原理相同
         时间复杂度：O(M + N)
         空间复杂度：O(1)
         */
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
            List<int[]> res = new ArrayList<>();
            int i = 0, j = 0;
            while (i < firstList.length && j < secondList.length) {
                int start = Math.max(firstList[i][0], secondList[j][0]), end = Math.min(firstList[i][1], secondList[j][1]);
                if (start <= end) res.add(new int[] {start, end});
                if (firstList[i][1] < secondList[j][1]) i++;
                else j++;
            }
            return res.toArray(new int[res.size()][]);
        }

    }


}
