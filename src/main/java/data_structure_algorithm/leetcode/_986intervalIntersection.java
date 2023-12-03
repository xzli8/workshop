package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _986intervalIntersection {

    public static class Solution1 {

        /**
         双指针：先将两个有序列表合并成一个有序列表，然后再找重叠区间
         (类似题："435.无重叠区间/452.用最少数量的箭引爆气球")
         */
        public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

            // 开辟新数组用于合并
            int m = firstList.length, n = secondList.length;
            int[][] mergeList = new int[m + n][2];

            // 双指针合并
            int i = 0, j = 0, k = 0;
            while (i < m && j < n) {
                if (firstList[i][0] < secondList[j][0]) mergeList[k++] = firstList[i++];
                else mergeList[k++] = secondList[j++];
            }
            while (i < m) mergeList[k++] = firstList[i++];
            while (j < n) mergeList[k++] = secondList[j++];

            // 找重叠区间
            List<int[]> res = new ArrayList<>();
            for (i = 1; i < m + n; i++) {
                if (mergeList[i][0] <= mergeList[i - 1][1]) {
                    res.add(new int[] {mergeList[i][0], mergeList[i - 1][1]});
                }
            }
            return res.toArray(new int[res.size()][]);
        }

        @Test
        public void test() {
            int[][] firstList = new int[][] {{3, 5}, {9, 20}};
            int[][] secondList = new int[][] {{4, 5}, {7, 10}, {11, 12}, {14, 15}, {16, 20}};
            System.out.println(intervalIntersection(firstList, secondList));
        }

    }


}
