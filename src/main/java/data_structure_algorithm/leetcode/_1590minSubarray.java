package data_structure_algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _1590minSubarray {

    public static class Solution1 {

        /**
         PreSum + HashMap
         TC: O(N)
         SC: O(N)
         */
        public int minSubarray(int[] nums, int p) {
            // corner case
            int k = 0;
            for (int num : nums) {
                k = (k + num) % p;
            }
            if (k == 0) return 0;

            Map<Integer, Integer> preSumMod2LastIdx = new HashMap<>();
            int n = nums.length, preSumMod = 0, minLen = n;
            for (int i = 0; i < n; i++) {
                preSumMod2LastIdx.put(preSumMod, i);
                preSumMod = (preSumMod + nums[i]) % p;
                if (preSumMod2LastIdx.containsKey((preSumMod - k + p) % p)) {
                    minLen = Math.min(minLen, i - preSumMod2LastIdx.get((preSumMod - k + p) % p) + 1);
                }
            }
            return minLen == n ? -1 : minLen;
        }

    }



    public static class Solution2 {

        public int minSubarray(int[] nums, int p) {
            int k = 0;
            for (int num : nums) {
                k = (k + num) % p;
            }
            if (k == 0) return 0;   // corner case

            int n = nums.length, preSumMod = 0, minLen = n;
            Map<Integer, Integer> preSumMod2LastIdx = new HashMap<>();
            preSumMod2LastIdx.put(0, -1);
            for (int i = 0; i < n; i++) {
                preSumMod = (preSumMod + nums[i]) % p;
                if (preSumMod2LastIdx.containsKey((preSumMod - k + p) % p)) {
                    minLen = Math.min(minLen, i - preSumMod2LastIdx.get((preSumMod - k + p) % p));
                }
                preSumMod2LastIdx.put(preSumMod, i);
            }
            return minLen == n ? -1 : minLen;
        }

    }

}
