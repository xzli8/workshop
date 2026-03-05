package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _3lengthOfLongestSubstring {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(M)，M为字符集大小
         */
        public int lengthOfLongestSubstring(String s) {
            int left = 0, right = 0, maxLen = 0;
            Set<Character> set = new HashSet<>();
            while (right < s.length()) {
                char cr = s.charAt(right++);
                while (set.contains(cr)) {
                    set.remove(s.charAt(left++));
                }
                set.add(cr);
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

        // 另一种写法，更麻烦点
        public int lengthOfLongestSubstringII(String s) {
            Set<Character> set = new HashSet<>();
            int n = s.length(), maxLen = 0, left = 0, right = 0;
            while (right < n) {
                while (right < n && !set.contains(s.charAt(right))) {
                    set.add(s.charAt(right++));
                }
                maxLen = Math.max(maxLen, right - left);
                while (right < n && set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left++));
                }
            }
            return maxLen;
        }


    }

}
