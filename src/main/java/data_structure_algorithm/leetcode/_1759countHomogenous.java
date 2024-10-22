package data_structure_algorithm.leetcode;

public class _1759countHomogenous {

    public static class Solution1 {

        /**
         SlideWindow
         */
        public int countHomogenous(String s) {
            long count = 0;
            int n = s.length(), left = 0, right = 0, mod = (int) 1e9 + 7;
            while (right < n) {
                while (right < n && s.charAt(right) == s.charAt(left)) right++;
                int len = right - left;
                count += (long) (len + 1) * len / 2;
                count %= mod;
                left = right;
            }
            return (int) count;
        }

    }

}
