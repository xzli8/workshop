package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _3043longestCommonPrefix {

    public static class Solution1 {

        /**
         HashSet: Put all the prefix of arr1 into a hashset, then iterate elements in arr2
         TC: O((N + M) * log^2 U)
         SC: O(Nlog^2 U)
         */
        public int longestCommonPrefix(int[] arr1, int[] arr2) {
            Set<String> prefixes = new HashSet<>();
            for (int num : arr1) {
                String s = Integer.toString(num);
                for (int i = 1; i <= s.length(); i++) {
                    prefixes.add(s.substring(0, i));
                }
            }

            int res = 0;
            for (int num : arr2) {
                String s = Integer.toString(num);
                for (int i = 1; i <= s.length(); i++) {
                    if (!prefixes.contains(s.substring(0, i))) {
                        break;
                    }
                    res = Math.max(res, i);
                }
            }
            return res;
        }

    }

}
