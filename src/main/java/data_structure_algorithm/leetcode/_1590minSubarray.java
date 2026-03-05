package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1590minSubarray {

    public static class Solution1 {

        /**
         * PreSum + Hash: O(N), O(N)
         */
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
                int mod = (preSumMod - k + p) % p;
                if (preSumMod2LastIdx.containsKey(mod)) {
                    minLen = Math.min(minLen, i - preSumMod2LastIdx.get(mod));
                }
                preSumMod2LastIdx.put(preSumMod, i);
            }
            return minLen == n ? -1 : minLen;
        }

    }

}
