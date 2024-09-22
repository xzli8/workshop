package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _395longestSubstring {

    public static class Solution1 {

        /**
         分治法:https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solutions/623991/jie-ben-ti-bang-zhu-da-jia-li-jie-di-gui-obla/
         思路：对于字符串s，如果存在某个字符c，它的出现次数大于0且小于k，则任何包含c的子串都不可能满足要求。
         也就是说，我们将字符串按照c切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，
         而不能跨越一个或多个段。
         */
        public int longestSubstring(String s, int k) {
            if (s.length() < k) return 0;
            Map<Character, Integer> counter = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
            }
            for (char c : counter.keySet()) {
                if (counter.get(c) < k) {
                    int res = 0;
                    for (String t : s.split(String.valueOf(c))) {
                        res = Math.max(res, longestSubstring(t, k));
                    }
                    return res;
                }
            }
            return s.length();
        }

    }

}
