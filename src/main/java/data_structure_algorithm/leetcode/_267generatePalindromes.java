package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _267generatePalindromes {

    /**
     * 给定一个字符串 s ，返回 其重新排列组合后可能构成的所有回文字符串，并去除重复的组合 。
     * 你可以按 任意顺序 返回答案。如果 s 不能形成任何回文排列时，则返回一个空列表。
     *
     * ref: https://leetcode.doocs.org/lc/267/
     */
    public static class Solution1 {

        private int n;
        private List<String> res = new ArrayList<>();
        private int[] counts = new int[26];

        /**
         * 哈希表+回溯: O(N*N!), O(N)
         * 回文排列需要满足至多有一个字符出现奇数次数。若不满足条件，答案提前返回。
         * 找到出现奇数次的字符，作为中间字符（可以为空），分别向两边扩展，构造回文串。若串的长度与原串长度相等，将该串添加到答案中。
         */
        public List<String> generatePalindromes(String s) {
            n = s.length();
            for (char c : s.toCharArray()) {
                counts[c - 'a']++;
            }

            String mid = null;
            for (int i = 0; i < 26; i++) {
                if (counts[i] % 2 == 1) {
                    if (mid != null) return res;    // 有两个奇数次数的字符，不可能组成回文
                    mid = String.valueOf((char) (i + 'a'));
                }
            }
            dfs(mid);
            return res;
        }

        private void dfs(String s) {
            if (s.length() == n) {
                res.add(s);
                return;
            }
            for (int i = 0; i < 26; i++) {
                if (counts[i] <= 1) continue;
                String c = String.valueOf((char) (i + 'a'));
                counts[i] -= 2;
                dfs(c + s + c);
                counts[i] += 2;
            }

        }

    }

}
