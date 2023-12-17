package data_structure_algorithm.leetcode;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class _340lengthOfLongestSubstringKDistinct {

    /**
     * 题目描述：给定一个字符串s，找出至多包含k个不同字符的最长子串T。
     * lintcode：https://www.lintcode.com/problem/386/
     *
     * 示例：
     * 输入: S = "eceba" 并且 k = 3
     * 输出: 4
     * 解释: T = "eceb"
     *
     * 输入: S = "WORLD" 并且 k = 4
     * 输出: 4
     * 解释: T = "WORL" 或 "ORLD"
     *
     */

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(M)，M为字符集大小
         */
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            // 记录字符及其出现次数
            HashMap<Character, Integer> window = new HashMap<>();

            // 滑动窗口
            int left = 0, right = 0, maxLen = 0;
            while (right < s.length()) {
                // 扩大窗口，移入字符
                char cr = s.charAt(right++);
                window.put(cr, window.getOrDefault(cr, 0) + 1);

                // 当不符合要求时，缩小窗口，移出字符
                while (window.size() > k) {
                    char cl = s.charAt(left++);
                    int count = window.get(cl) - 1;
                    if (count == 0) {
                        window.remove(cl);
                    } else {
                        window.put(cl, count);
                    }
                }

                // 更新最长长度
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        @Test
        public void test() {
            Assert.assertEquals(4, lengthOfLongestSubstringKDistinct("eceba", 3));
            Assert.assertEquals(4, lengthOfLongestSubstringKDistinct("WORLD", 4));
        }

    }

}
