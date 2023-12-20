package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _217containsDuplicate {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(M)，M为nums的范围
         */
        public boolean containsDuplicate(int[] nums) {
            Set<Integer> s = new HashSet<>();
            for (int num : nums) {
                if (s.contains(num)) {
                    return true;
                }
                s.add(num);
            }
            return false;
        }

    }

}
