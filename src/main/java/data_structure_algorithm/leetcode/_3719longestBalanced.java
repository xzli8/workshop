package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _3719longestBalanced {

    public static class Solution1 {

        /**
         哈希表+枚举: O(N^2), O(N)
         */
        public int longestBalanced(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length; i++) {
                Set<Integer> visited = new HashSet<>();
                int oddNum = 0, evenNum = 0;
                for (int j = i; j < nums.length; j++) {
                    // returns true if this set did not already contain the specified element
                    if (visited.add(nums[j])) {
                        if (nums[j] % 2 == 0) evenNum++;
                        else oddNum++;
                    }
                    if (evenNum == oddNum) res = Math.max(res, j - i + 1);
                }
            }
            return res;
        }

    }

}
