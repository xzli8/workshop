package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _1094carPooling {

    public static class Solution1 {

        /**
         1.差分数组（多次修改区间，返回最终结果）
         */
         public boolean carPooling(int[][] trips, int capacity) {
             int[] nums = new int[1001];
             Difference diff = new Difference(nums);
             for (int[] trip : trips) {
                 // from时上车，to时下车，所以to需要多减1
                 diff.update(trip[1], trip[2] - 1, trip[0]);
             }
             nums = diff.result();

             for (int i = 0; i < nums.length; i++) {
                 if (nums[i] > capacity) {
                     return false;
                 }
             }
             return true;
         }

         public class Difference {

             private int[] diff;

             public Difference(int[] nums) {
                 diff = new int[nums.length];
                 diff[0] = nums[0];
                 for (int i = 1; i < nums.length; i++) {
                     diff[i] = nums[i] - nums[i-1];
                 }
             }

             public void update(int from, int to, int num) {
                 diff[from] += num;
                 if (to + 1 < diff.length) {
                     diff[to + 1] -= num;
                 }
             }

             public int[] result() {
                 int[] nums = new int[diff.length];
                 nums[0] = diff[0];
                 for (int i = 1; i < diff.length; i++) {
                     nums[i] = nums[i-1] + diff[i];
                 }
                 return nums;
             }

         }

    }



    public static class Solution2 {

        /**
         排序 + 小顶堆（类似：会议室II）
         */
        public boolean carPooling(int[][] trips, int capacity) {

            // 按照上车位置排序
            Arrays.sort(trips, new Comparator<int[]>() {
                public int compare(int[] trip1, int[] trip2) {
                    return trip1[1] - trip2[1];
                }
            });

            // 小顶堆记录下车位置和对应人数
            PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> t1[2] - t2[2]);
            for (int[] trip : trips) {
                // 上车前，先计算下车人数
                while (!pq.isEmpty() && trip[1] >= pq.peek()[2]) {
                    // 下车并腾出位置
                    int[] tmp = pq.poll();
                    capacity += tmp[0];
                }
                capacity -= trip[0];
                if (capacity < 0) {
                    return false;
                }
                pq.offer(trip);
            }
            return true;
        }

    }



}
