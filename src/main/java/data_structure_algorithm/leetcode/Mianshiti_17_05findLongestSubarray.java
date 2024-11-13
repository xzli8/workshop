package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Mianshiti_17_05findLongestSubarray {

    public static class Solution1 {

        /**
         PreSum + HashMap: Replace character with 1 and digit with -1
         TC: O(N)
         SC: O(N)
         */
        public String[] findLongestSubarray(String[] array) {
            int n = array.length, preSum = 0, maxLen = 0, startIdx = -1;
            Map<Integer, Integer> preSum2Idx = new HashMap<>();
            preSum2Idx.put(0, -1);
            for (int i = 0; i < n; i++) {
                preSum += (Character.isLetter(array[i].charAt(0)) ? 1 : -1);
                if (preSum2Idx.containsKey(preSum)) {
                    int firstIdx = preSum2Idx.get(preSum);
                    if (i - firstIdx > maxLen) {
                        maxLen = i - firstIdx;
                        startIdx = firstIdx + 1;
                    }
                } else {
                    // store the earliest index
                    preSum2Idx.put(preSum, i);
                }
            }
            if (maxLen == 0) return new String[0];
            String[] res = new String[maxLen];
            System.arraycopy(array, startIdx, res, 0, maxLen);
            return res;
        }

    }

}
