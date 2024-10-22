package data_structure_algorithm.leetcode;

public class _1513numSub {

    public static class Solution1 {

        /**
         SlideWindow + Math
         */
        public int numSub(String s) {
            int n = s.length(), left = 0, right = 0, mod = (int) 1e9 + 7;
            long count = 0;
            while (right < n) {
                while (left < n && s.charAt(left) == '0') left++;
                right = left;
                while (right < n && s.charAt(right) == '1') right++;
                int len = right - left;
                count += (long) (len + 1) * len / 2;
                count %= mod;
                left = right + 1;
            }
            return (int) count;
        }

    }

}
