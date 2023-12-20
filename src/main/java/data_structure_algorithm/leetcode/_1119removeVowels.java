package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

public class _1119removeVowels {

    /**
     * 题目链接：https://www.icode9.com/content-4-1385344.html
     */

    public static class Solution1 {

        @Test
        public void test() {
            Assert.assertEquals("ltcdscmmntyfrcdrs", removeVowels("leetcodeisacommunityforcoders"));
            Assert.assertEquals("", removeVowels("aeiou"));
        }

        /**
         * 双指针
         *  时间复杂度：O(N)
         *  空间复杂度：O(N)
         */
        public String removeVowels(String s) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (!isVowel(c)) {
                    res.append(c);
                }
            }
            return res.toString();
        }

        private boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }

    }

}
