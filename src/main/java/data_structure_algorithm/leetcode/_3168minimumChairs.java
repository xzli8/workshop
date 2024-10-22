package data_structure_algorithm.leetcode;

public class _3168minimumChairs {

    public static class Solution1 {

        /**
         Mock
         */
        public int minimumChairs(String s) {
            int count = 0, res = 0;
            for (char c : s.toCharArray()) {
                if (c == 'E') count++;
                else count--;
                res = Math.max(res, count);
            }
            return res;
        }

    }

}
