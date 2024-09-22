package data_structure_algorithm.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class _2996missingInteger {

    public static class Solution1 {

        /**
         Hash
         */
        public int missingInteger(int[] nums) {
            // find the longest sequential prefix
            int n = nums.length, sum = nums[0];
            int i = 1;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
                i++;
            }

            // find k
            Set<Integer> s = Arrays.stream(nums).boxed().collect(Collectors.toSet());
            while (s.contains(sum)) {
                sum++;
            }
            return sum;
        }

    }

}
