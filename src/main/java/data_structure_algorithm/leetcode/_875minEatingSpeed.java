package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _875minEatingSpeed {

    public static class Solution1 {

        /**
         值域二分:O(NlogU), O(1)
         */
        public int minEatingSpeed(int[] piles, int h) {
            int min = 1, max = Arrays.stream(piles).max().getAsInt();
            while (min <= max) {
                int mid = min + ((max - min) >> 1);
                // 耗时小于等于h，看mid能否再小点：如果不能，说明mid就是最小的，返回；如果可以，继续减小mid
                if (costHours(piles, mid) <= h) {
                    if (mid == 1 || costHours(piles, mid - 1) > h) return mid;
                    else max = mid - 1;
                }
                // 耗时大于h，不满足，需要提高速度
                else {
                    min = mid + 1;
                }
            }
            return -1;
        }

        private int costHours(int[] piles, int k) {
            int hours = 0;
            for (int pile : piles) {
                hours += pile / k + (pile % k == 0 ? 0 : 1);    // 向上取整
                // hours += (pile + k - 1) / k;    // 向上取整也可以这样写
            }
            return hours;
        }

    }

}
