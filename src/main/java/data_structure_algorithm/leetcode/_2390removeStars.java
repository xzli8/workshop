package data_structure_algorithm.leetcode;

public class _2390removeStars {

    public static class Solution1 {

        /**
         Stack + Simulation
         */
        public String removeStars(String s) {
            // Use StringBuilder to mock stack
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (c != '*') {
                    sb.append(c);
                } else {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }
            return sb.toString();
        }

    }

}
