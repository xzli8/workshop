package data_structure_algorithm.leetcode;

public class _2734smallestString {

    public static class Solution1 {

        /**
         Greedy: position of 'a'
         ref:https://leetcode.cn/problems/lexicographically-smallest-string-after-substring-operation/solutions/2304936/tan-xin-pythonjavacgo-by-endlesscheng-gm1d/
         */
        public String smallestString(String s) {
            char[] cs = s.toCharArray();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (cs[i] > 'a') {
                    while (i < n && cs[i] > 'a') {
                        cs[i]--;
                        i++;
                    }
                    return new String(cs);
                }
            }

            // all chars are 'a'
            cs[n - 1] = 'z';
            return new String(cs);
        }

    }

}
