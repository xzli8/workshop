package data_structure_algorithm.leetcode;

public class _344reverseString {


    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public void reverseString(char[] s) {
            int l = 0, r = s.length - 1;
            while (l < r) {
                swap(s, l++, r--);
            }
        }

        private void swap(char[] s, int i, int j) {
            char c = s[i];
            s[i] = s[j];
            s[j] = c;
        }

    }

}
