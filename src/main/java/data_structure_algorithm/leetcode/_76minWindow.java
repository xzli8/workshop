package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _76minWindow {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String minWindow(String s, String t) {
            Map<Character, Integer> needs = new HashMap<>();
            Map<Character, Integer> count = new HashMap<>();
            for (char c : t.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }
            int start = 0, end = 0, match = 0, minLen = Integer.MAX_VALUE, left = 0;
            while (end < s.length()) {
                char c1 = s.charAt(end);
                if (needs.containsKey(c1)) {
                    count.put(c1, count.getOrDefault(c1, 0) + 1);
                    // 注意这里不能用"=="比较，因为Integer采用享元模式(-128, 127)之间的值会共用同一个，而"=="比较的是地址
                    if (count.get(c1).equals(needs.get(c1))) {
                        match++;
                    }
                }
                end++;

                while (match == needs.size()) {
                    if (end - start < minLen) {
                        minLen = end - start;
                        left = start;
                    }
                    char c2 = s.charAt(start);
                    if (needs.containsKey(c2)) {
                        if (count.get(c2).equals(needs.get(c2))) {
                            match--;
                        }
                        count.put(c2, count.getOrDefault(c2, 0) - 1);
                    }
                    start++;
                }
            }
            return minLen == Integer.MAX_VALUE ? "" : s.substring(left, left + minLen);
        }

    }

}
