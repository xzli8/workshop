package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _438findAnagrams {

    public static class Solution1 {

        /**
         滑动窗口
         相似题：https://leetcode.cn/problems/permutation-in-string/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<Integer> findAnagrams(String s, String p) {
            // 计算p中每个字符出现的次数(可以用map计数，也可以用int[]作为哈希表计数，因为s,p仅包含小写字母)
            Map<Character, Integer> needs = new HashMap<>();
            for (char c : p.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            List<Integer> res = new ArrayList<>();
            int left = 0, right = 0, match = 0;
            Map<Character, Integer> window = new HashMap<>();
            while (right < s.length()) {
                // 不满足条件时，移动右指针，找可行解
                char cr = s.charAt(right++);
                if (needs.containsKey(cr)) {
                    window.put(cr, window.getOrDefault(cr, 0) + 1);
                    if (window.get(cr).equals(needs.get(cr))) {
                        match++;
                    }
                }

                // 满足条件后，移动左指针，找最优解
                while (right - left == p.length()) {
                    if (match == needs.size()) {
                        res.add(left);
                    }

                    char lc = s.charAt(left++);
                    if (needs.containsKey(lc)) {
                        if (window.get(lc).equals(needs.get(lc))) {
                            match--;
                        }
                        window.put(lc, window.getOrDefault(lc, 0) - 1);
                    }
                }
            }
            return res;
        }

    }

}
