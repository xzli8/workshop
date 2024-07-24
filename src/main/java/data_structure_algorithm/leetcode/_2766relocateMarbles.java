package data_structure_algorithm.leetcode;

import java.util.*;

public class _2766relocateMarbles {

    public static class Solution1 {

        /**
         Hash + Simulation
         TimeComplexity:
         SpaceComplexity:
         Reference: https://leetcode.cn/problems/relocate-marbles/solutions/2336489/jian-ji-xie-fa-by-endlesscheng-thul
         */
        public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
            Set<Integer> set = new HashSet<>(nums.length);
            for (int num : nums) set.add(num);
            for (int i = 0; i < moveFrom.length; i++) {
                set.remove(moveFrom[i]);
                set.add(moveTo[i]);
            }
            List<Integer> res = new ArrayList<>(set);
            Collections.sort(res);
            return res;
        }

    }

}
