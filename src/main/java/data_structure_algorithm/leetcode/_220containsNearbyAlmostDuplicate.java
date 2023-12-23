package data_structure_algorithm.leetcode;

import java.util.TreeMap;

public class _220containsNearbyAlmostDuplicate {

    public static class Solution1 {

        /**
         滑动窗口 + TreeMap
         思路：维护窗口大小不超过indexDiff，用TreeMap记录窗口内的数字及其出现次数，
         每遍历到一个数时，从窗口内找最大的小于该数的数和最小的大于该数的数，进行比较

         时间复杂度：O(NlogK)
         空间复杂度：O(K)
         */
        public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
            int n = nums.length, left = 0, right = 0;
            TreeMap<Integer, Integer> num2Count = new TreeMap<>();
            while (right < n) {
                int nr = nums[right++];
                Integer maxLt = num2Count.floorKey(nr);
                if (maxLt != null && (long) nr - (long) maxLt <= valueDiff) {
                    return true;
                }
                Integer minGt = num2Count.ceilingKey(nr);
                if (minGt != null && (long) minGt - (long) nr <= valueDiff) {
                    return true;
                }

                num2Count.put(nr, num2Count.getOrDefault(nr, 0) + 1);
                if (right - left > indexDiff) {
                    int nl = nums[left++];
                    num2Count.put(nl, num2Count.get(nl) - 1);
                    if (num2Count.get(nl) == 0) {
                        num2Count.remove(nl);
                    }
                }
            }
            return false;
        }

    }

}
