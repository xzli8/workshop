package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _992subarraysWithKDistinct {

    public static class Solution1 {

        /**
         滑动窗口
         */
        public int subarraysWithKDistinct(int[] nums, int k) {
            Map<Integer, Integer> counts = new HashMap<>();
            int n = nums.length, left = 0, right = 0, count = 0;
            while (right < n) {
                while (right < n && counts.size() < k) {
                    int nr = nums[right++];
                    counts.put(nr, counts.getOrDefault(nr, 0) + 1);
                }
                count++;
                while (right < n && counts.containsKey(nums[right])) {
                    counts.put(nums[right], counts.get(nums[right]) + 1);
                    right++;
                    count++;
                }
                while (counts.size() == k) {
                    int nl = nums[left++];
                    counts.put(nl, counts.get(nl) - 1);
                    if (counts.get(nl) == 0) {
                        counts.remove(nl);
                    }
                    if (counts.size() == k) count++;
                }
            }
            return count;
        }

        @Test
        public void test() {
            Assert.assertEquals(7, subarraysWithKDistinct(new int[] {1,2,1,2,3}, 2));
            Assert.assertEquals(3, subarraysWithKDistinct(new int[] {1,2,1,3,4}, 3));
        }

    }

}
