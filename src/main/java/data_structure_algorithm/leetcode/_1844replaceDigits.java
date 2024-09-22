package data_structure_algorithm.leetcode;

public class _1844replaceDigits {

    public static class Solution1 {

        /**
         simulate
         */
        public String replaceDigits(String s) {
            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    sb.append(s.charAt(i));
                } else {
                    sb.append((char) (s.charAt(i - 1) + (s.charAt(i) - '0')));
                }
            }
            return sb.toString();
        }

    }

}
