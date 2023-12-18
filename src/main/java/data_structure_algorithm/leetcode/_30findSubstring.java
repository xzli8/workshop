package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _30findSubstring {

    public static class Solution1 {

        /**
         滑动窗口(类似题："76.最小覆盖子串；567.字符串的排列")
         时间复杂度：O(ls * n)，ls为s的长度，n为word的长度
         空间复杂度：O(m * n)，m为words的单词数，n为word的长度
         */
        public List<Integer> findSubstring(String s, String[] words) {
            // 统计words中各单词出现次数
            Map<String, Integer> needs = new HashMap<>();
            for (String word : words) {
                needs.put(word, needs.getOrDefault(word, 0) + 1);
            }

            // 结果数组
            List<Integer> res = new ArrayList<>();

            // 将s分割成字符串数组，每个字符串的长度等于word的长度，共有n中分割方式(分别删除第0~n-1个字符，分割剩余部分)
            int m = words.length, n = words[0].length();
            for (int start = 0; start < n; start++) {
                // 滑动窗口(与"567.字符串的排列"类似)
                int left = start, right = start, match = 0;
                Map<String, Integer> window = new HashMap<>();

                // 这里要保证右边界能移动到最后一个字母，所以要有等号
                while (right + n <= s.length()) {
                    // 右指针右移，扩大窗口，更新窗口内单词出现次数
                    String wr = s.substring(right, right + n);
                    right += n;
                    if (needs.containsKey(wr)) {
                        window.put(wr, window.getOrDefault(wr, 0) + 1);
                        if (window.get(wr).equals(needs.get(wr))) {
                            match++;
                        }
                    }

                    // 当窗口大小刚好等于排列长度时
                    if (right - left == m * n) {
                        // 当所有字符都匹配时，找到一个可行解
                        if (match == needs.size()) {
                            res.add(left);
                        }

                        // 左指针右移，缩小窗口，更新窗口内单词出现次数
                        String wl = s.substring(left, left + n);
                        left += n;
                        if (needs.containsKey(wl)) {
                            if (window.get(wl).equals(needs.get(wl))) {
                                match--;
                            }
                            window.put(wl, window.get(wl) - 1);
                        }
                    }
                }
            }
            return res;
        }

    }

}
