package data_structure_algorithm.leetcode;

public class _2129capitalizeTitle {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String capitalizeTitle(String title) {
            StringBuilder sb = new StringBuilder(title);
            int n = title.length(), left = 0, right = 0;
            while (right < n) {
                while (right < n && title.charAt(right) != ' ') right++;
                if (right - left > 2) sb.setCharAt(left, Character.toUpperCase(sb.charAt(left++)));
                while (left < right) sb.setCharAt(left, Character.toLowerCase(sb.charAt(left++)));
                left = ++right;
            }
            return sb.toString();
        }

    }

}
