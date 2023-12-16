package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _567checkInclusion {

    public static class Solution1 {

        /**
         滑动窗口：(类似题："438.找到字符串中所有字母异位词")
         NOTE：因为s1,s2仅包含小写字母，所以可以用哈希数组替代哈希map
         时间复杂度：O(N)，N为s2的长度
         空间复杂度：O(1)，与字符集大小有关，这里s1,s2仅包含小写字母，所以这是个常量
         */
        public boolean checkInclusion(String s1, String s2) {
            // 统计s1中每个字符出现的次数
            Map<Character, Integer> needs = new HashMap<>();
            for (char c : s1.toCharArray()) {
                needs.put(c, needs.getOrDefault(c, 0) + 1);
            }

            // 滑动窗口
            int left = 0, right = 0, match = 0;
            Map<Character, Integer> window = new HashMap<>();
            while (right < s2.length()) {
                // 右指针右移，扩大窗口，找可行解
                char cr = s2.charAt(right++);
                if (needs.containsKey(cr)) {
                    window.put(cr, window.getOrDefault(cr, 0) + 1);
                    if (window.get(cr).equals(needs.get(cr))) {
                        match++;
                    }
                }

                // 当窗口大小等于s1长度时，判断是否满足条件(这里用if代替while也可以)
                while (right - left == s1.length()) {
                    if (match == needs.size()) {
                        return true;
                    }

                    // 左指针右移，缩小窗口，以便下一次循环开始时能够右移右指针，保持窗口大小不变
                    char cl = s2.charAt(left++);
                    if (needs.containsKey(cl)) {
                        if (window.get(cl).equals(needs.get(cl))) {
                            match--;
                        }
                        window.put(cl, window.get(cl) - 1);
                    }
                }
            }
            return false;
        }

    }



    public static class Solution2 {

        /**
         滑动窗口：因为s1,s2仅包含小写字母，所以可以用哈希数组代替哈希表
         */
        public boolean checkInclusion(String s1, String s2) {
            // 统计s1中所有字符的出现次数
            int[] needs = new int[26];
            for (char c : s1.toCharArray()) {
                needs[c - 'a']++;
            }

            // 统计s1中不同字符的数量
            int size = 0;
            for (int count : needs) {
                if (count > 0) {
                    size++;
                }
            }

            // 滑动窗口
            int left = 0, right = 0, match = 0;
            int[] window = new int[26];
            while (right < s2.length()) {
                // 右指针右移，扩大窗口，找可行解
                int ir = s2.charAt(right++) - 'a';
                if (needs[ir] > 0) {
                    if (++window[ir] == needs[ir]) {
                        match++;
                    }
                }

                // 当窗口长度等于s1的长度时，判断是否有可行解
                while (right - left == s1.length()) {
                    if (match == size) {
                        return true;
                    }

                    // 左指针右移，为下一次循环做准备
                    int il = s2.charAt(left++) - 'a';
                    if (needs[il] > 0) {
                        if (window[il]-- == needs[il]) {
                            match--;
                        }
                    }
                }
            }
            return false;
        }

    }



}
