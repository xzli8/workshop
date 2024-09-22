package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _1943splitPainting {

    public static class Solution0 {

        /**
         TreeMap:每一次绘画都会产生一段颜色的变化，只需要在绘画开始的地方记录颜色值增加，结束的地方记录颜色值减少，最后遍历一遍即可完成合并。
         ref: https://leetcode.cn/problems/describe-the-painting/solutions/894617/c-ji-lu-yan-se-fa-sheng-bian-hua-de-di-f-a7kh/
         */
        public List<List<Long>> splitPainting(int[][] segments) {
            Map<Integer, Long> diff = new TreeMap<>();
            for (int[] segment : segments) {
                int start = segment[0], end = segment[1], color = segment[2];
                diff.put(start, diff.getOrDefault(start, 0L) + color);
                diff.put(end, diff.getOrDefault(end, 0L) - color);
            }

            List<List<Long>> res = new ArrayList<>();
            int last = 0;
            long color = 0;
            for (int idx : diff.keySet()) {
                if (color != 0) {
                    List<Long> segment = new ArrayList<>();
                    segment.add((long) last);
                    segment.add((long) idx);
                    segment.add(color);
                    res.add(segment);
                }
                last = idx;
                color += diff.get(idx);
            }
            return res;
        }

    }



    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(splitPainting(new int[][]{{1,4,5}, {4,7,7}, {1,7,9}}));
            System.out.println(splitPainting(new int[][]{{1,4,5}, {1,4,7}, {4,7,1}, {4,7,11}}));    // error
        }

        /**
         Difference Array (x)
         (Cannot meet the condition that every color is different)
         */
         public List<List<Long>> splitPainting(int[][] segments) {
             // update
             int n = (int) 1e5 + 1;
             long[] nums = new long[n];
             Difference diff = new Difference(nums);
             for (int[] segment : segments) {
                 diff.update(segment[0], segment[1] - 1, segment[2]);
             }
             nums = diff.result();

             // convert
             List<List<Long>> res = new ArrayList<>();
             int left = 0, right = 0;
             while (left < n) {
                 while (left < n && nums[left] == 0) {
                     left++;
                 }
                 if (left >= n) break;
                 right = left + 1;
                 while (right < n && nums[right] == nums[left]) {
                     right++;
                 }
                 List<Long> segment = new ArrayList<>();
                 segment.add((long) left);
                 segment.add((long) (right));
                 segment.add(nums[left]);
                 res.add(segment);
                 left = right;
             }
             return res;
         }

         class Difference {

             private long[] diff;

             public Difference(long[] nums) {
                 int n = nums.length;
                 diff = new long[n];
                 diff[0] = nums[0];
                 for (int i = 1; i < n; i++) {
                     diff[i] = nums[i] - nums[i - 1];
                 }
             }

             public void update(int start, int end, long num) {
                 diff[start] += num;
                 if (end + 1 < diff.length) {
                     diff[end + 1] -= num;
                 }
             }

             public long[] result() {
                 int n = diff.length;
                 long[] res = new long[n];
                 res[0] = diff[0];
                 for (int i = 1; i < n; i++) {
                     res[i] = res[i - 1] + diff[i];
                 }
                 return res;
             }

         }

    }

}
