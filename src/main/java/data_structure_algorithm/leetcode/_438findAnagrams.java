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

                    char cl = s.charAt(left++);
                    if (needs.containsKey(cl)) {
                        if (window.get(cl).equals(needs.get(cl))) {
                            match--;
                        }
                        window.put(cl, window.getOrDefault(cl, 0) - 1);
                    }
                }
            }
            return res;
        }

    }



    public static class Solution2 {

        /**
         滑动窗口：因为s和p仅包含小写字母，所以可以用哈希数组代替哈希map
         */
        public List<Integer> findAnagrams(String s, String p) {
            // 统计p中所有字符的出现数量
            int[] needs = new int[26];
            for (char c : p.toCharArray()) {
                needs[c - 'a']++;
            }

            // 统计p中不同的字符数量
            int size = 0;
            for (int count : needs) {
                if (count > 0) {
                    size++;
                }
            }

            // 滑动窗口
            List<Integer> res = new ArrayList<>();
            int left = 0, right = 0, match = 0;
            int[] window = new int[26];
            while (right < s.length()) {
                // 右指针右移，扩大窗口，找可行解
                int ir = s.charAt(right++) - 'a';
                if (needs[ir] > 0) {
                    if (++window[ir] == needs[ir]) {
                        match++;
                    }
                }

                // 窗口大小刚好等于p的长度时，判断是否有可行解
                while (right - left == p.length()) {
                    if (match == size) {
                        res.add(left);
                    }

                    // 左指针右移，缩小窗口，为下一次循环做准备
                    int il = s.charAt(left++) - 'a';
                    if (needs[il] > 0) {
                        if (window[il]-- == needs[il]) {
                            match--;
                        }
                    }
                }
            }
            return res;
        }

    }

}
