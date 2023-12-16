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
                char in = s.charAt(right++);
                while (set.contains(in)) {
                    set.remove(s.charAt(left++));
                }
                set.add(in);
                maxLen = Math.max(maxLen, right - left);
            }
            return maxLen;
        }

    }

}
