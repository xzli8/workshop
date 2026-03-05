package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _395longestSubstring {

    public static class Solution1 {

        /**
         分治法: O(N), O(1)
         Note：先统计每个字符的出现次数。对于字符串s，如果存在某个字符c，它的出现次数大于0且小于k，则任何包含c的子串都不可能满足要求。
            也就是说，我们将字符串按照c切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，而不能跨越一个或多个段。
         Ref: https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solutions/623991/jie-ben-ti-bang-zhu-da-jia-li-jie-di-gui-obla/
         */
        public int longestSubstring(String s, int k) {
            if (s.length() < k) return 0;

            // 遍历字符串，统计每个字符的出现次数
            Map<Character, Integer> counter = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
            }

            // 找到出现次数小于k的字符并进行切分
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


    public static class Solution2 {

        /**
         滑动窗口
         Note：为什么不能用简单的滑动窗口？因为不满足单向性(二段性，存在某个明确的位置能将区间一分为二)。[https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solutions/624045/xiang-jie-mei-ju-shuang-zhi-zhen-jie-fa-50ri1/]
         Ref: https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/solutions/623432/zhi-shao-you-kge-zhong-fu-zi-fu-de-zui-c-o6ww/
         */

    }

}
