package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _159lengthOfLongestSubstringTwoDistinct {

    /**
     * 题目描述:给定一个字符串s，找出至多包含两个不同字符的最长子串t，并返回该子串的长度。
     *
     * 示例：
     *  输入: “eceba”
     *  输出: 3
     *  解释:
     *  T 是 "ece" 它的长度是 3.
     *
     *  输入: “aaa”
     *  输出: 3
     *
     */

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(M)，M是字符集的大小
         */
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            Map<Character, Integer> counts = new HashMap<>();
            int n = s.length(), left = 0, right = 0, maxLen = 0;
            while (right < n) {
                char cr = s.charAt(right++);
                counts.put(cr, counts.getOrDefault(cr, 0) + 1);

                while (counts.size() > 2) {
                    char cl = s.charAt(left++);
                    counts.put(cl, counts.get(cl) - 1);
                    if (counts.get(cl) == 0) {
                        counts.remove(cl);
                    }
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        @Test
        public void test() {
            Assert.assertEquals(3, lengthOfLongestSubstringTwoDistinct("eceba"));
            Assert.assertEquals(3, lengthOfLongestSubstringTwoDistinct("aaa"));
        }

    }

}
