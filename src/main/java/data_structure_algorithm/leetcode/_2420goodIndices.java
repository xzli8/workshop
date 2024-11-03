package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _2420goodIndices {

    public static class Solution1 {

        /**
         Precompute
         */
        public List<Integer> goodIndices(int[] nums, int k) {
            int n = nums.length;
            int[] before = new int[n], after = new int[n];
            Arrays.fill(before, 1);
            Arrays.fill(after, 1);
            for (int i = 1; i < n; i++) {
                if (nums[i] <= nums[i - 1]) {
                    before[i] = before[i - 1] + 1;
                }
            }
            for (int i = n - 2; i >= 0; i--) {
                if (nums[i] <= nums[i + 1]) {
                    after[i] = after[i + 1] + 1;
                }
            }

            List<Integer> res = new ArrayList<>();
            for (int i = k; i < n - k; i++) {
                if (before[i - 1] >= k && after[i + 1] >= k) {
                    res.add(i);
                }
            }
            return res;
        }


    }

}
