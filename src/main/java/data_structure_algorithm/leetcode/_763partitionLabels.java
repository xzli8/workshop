package data_structure_algorithm.leetcode;

import java.util.*;

public class _763partitionLabels {

    public static class Solution1 {

         /**
             合并区间（56）
          */
         public List<Integer> partitionLabels(String s) {
             // 遍历字符串，计算每个字符最先出现的位置和最后出现的位置，形成一个区间数组
             Map<Character, int[]> intervalMap = new HashMap<>();
             for (int i = 0; i < s.length(); i++) {
                 char c = s.charAt(i);
                 int[] interval = intervalMap.getOrDefault(c, new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
                 interval[0] = Math.min(i, interval[0]);
                 interval[1] = Math.max(i, interval[1]);
                 intervalMap.put(c, interval);
             }

             // 区间数组按照区间起点排序
             int[][] intervals = intervalMap.values().toArray(new int[0][]);
             Arrays.sort(intervals, new Comparator<int[]>() {
                 public int compare(int[] interval1, int[] interval2) {
                     return interval1[0] - interval2[0];
                 }
             });

             // 合并重复区间
             List<int[]> distinctIntervals = new ArrayList<>();
             for (int i = 0; i < intervals.length; i++) {
                 int n = distinctIntervals.size();
                 if (n == 0 || distinctIntervals.get(n-1)[1] < intervals[i][0]) {
                     distinctIntervals.add(intervals[i]);
                 } else {
                     distinctIntervals.get(n-1)[1] = Math.max(intervals[i][1], distinctIntervals.get(n-1)[1]);
                 }
             }

             List<Integer> res = new ArrayList<>();
             for (int i = 0; i < distinctIntervals.size(); i++) {
                 res.add(distinctIntervals.get(i)[1] - distinctIntervals.get(i)[0] + 1);
             }
             return res;
         }


    }



    public static class Solution2 {

        /**
         贪心(参考官方题解)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<Integer> partitionLabels(String s) {
            // 找每个字母最后出现的位置
            int[] lastIndex = new int[26];
            for (int i = 0; i < s.length(); i++) {
                lastIndex[s.charAt(i) - 'a'] = i;
            }

            // 贪心法找最小区间
            List<Integer> res = new ArrayList<>();
            int start = 0, end = 0;
            for (int i = 0; i < s.length(); i++) {
                end = Math.max(end, lastIndex[s.charAt(i) - 'a']);
                if (i == end) {
                    res.add(end - start + 1);
                    start = end + 1;
                }
            }
            return res;
        }

    }


}
