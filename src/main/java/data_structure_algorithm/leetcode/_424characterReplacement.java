package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _424characterReplacement {

    public static class Solution1 {

        /**
         滑动窗口:窗口内出现次数最多的字符+k大于等于串长度，这个窗口就满足条件
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int characterReplacement(String s, int k) {
            // s只包含大写字母，可以用哈希数组代替哈希表
            int[] counts = new int[26];
            int n = s.length(), left = 0, right = 0, maxCount = 0, maxLen = 0;
            while (right < n) {
                // 右指针指向的字符计数+1，找出现次数最多的字符数量，右指针右移
                maxCount = Math.max(maxCount, ++counts[s.charAt(right++) - 'A']);

                // 当出现次数最多的字符的数量 + k < 窗口大小时，不满足条件，左指针右移
                while (maxCount + k < right - left) {
                    counts[s.charAt(left++) - 'A']--;
                }

                // 更新最大长度
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        public int characterReplacementII(String s, int k) {
            // 不仅包含大写字母时的通用解法：map计数
            int maxLen = 0, maxCount = 0, left = 0, right = 0;
            Map<Character, Integer> count = new HashMap<>();
            while (right < s.length()) {
                char rc = s.charAt(right++);
                count.put(rc, count.getOrDefault(rc, 0) + 1);
                maxCount = Math.max(maxCount, count.get(rc));
                while (maxCount + k < right - left) {
                    char lc = s.charAt(left++);
                    count.put(lc, count.get(lc) - 1);
                }
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

    }

}
