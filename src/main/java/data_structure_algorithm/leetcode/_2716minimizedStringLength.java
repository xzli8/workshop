package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _2716minimizedStringLength {

    public static class Solution1 {

        /**
         HashSet
         */
        public int minimizedStringLength(String s) {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                set.add(c);
            }
            return set.size();
        }

    }

}
