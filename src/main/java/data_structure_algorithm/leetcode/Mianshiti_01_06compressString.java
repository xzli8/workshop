package data_structure_algorithm.leetcode;

import org.junit.Test;

public class Mianshiti_01_06compressString {

    public static class Solution1 {

        @Test
        public void test() {
            System.out.println(compressString("bbbac"));
        }

        /**
         双指针
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String compressString(String S) {
            if (S.length() == 1) return S;
            StringBuilder sb = new StringBuilder();
            int n = S.length(), left = 0, right = 1;
            while (right <= n) {
                while (right < n && S.charAt(right) == S.charAt(right - 1)) right++;
                sb.append(S.charAt(left)).append(right - left);
                left = right++;
            }
            return sb.length() < n ? sb.toString() : S;
        }

    }

}
