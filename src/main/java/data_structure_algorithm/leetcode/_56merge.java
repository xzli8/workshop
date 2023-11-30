package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _56merge {

    public static class Solution1 {


        /**
         排序 + 合并
         时间复杂度：O(NlogN)
         空间复杂度：O(logN)
         */
        public int[][] merge(int[][] intervals) {
            // 将所有区间按照起始端点的大小升序排列
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] interval1, int[] interval2) {
                    return interval1[0] - interval2[0];
                }
            });

            // 合并
            List<int[]> res = new ArrayList<>();
            for (int i = 0; i < intervals.length; i++) {
                int n = res.size();
                if (n == 0 || res.get(n-1)[1] < intervals[i][0]) {
                    res.add(intervals[i]);
                } else {
                    res.get(n-1)[1] = Math.max(res.get(n-1)[1], intervals[i][1]);
                }
            }
            return res.toArray(new int[res.size()][]);
        }

    }

}
