package data_structure_algorithm.leetcode;

public class _767reorganizeString {

    public static class Solution1 {

        /**
         贪心：将出现次数最多的字符放在下标为偶数的位置上
         ref:https://leetcode.cn/problems/reorganize-string/solutions/503439/javadai-ma-ji-bai-liao-100de-yong-hu-by-sdwwld/
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String reorganizeString(String s) {
            // 统计每个字符出现的次数
            int[] counts = new int[26];
            for (int i = 0; i < s.length(); i++) {
                counts[s.charAt(i) - 'a']++;
            }

            // 找出现次数最多的字符
            int maxCount = 0, c = 0;
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] > maxCount) {
                    maxCount = counts[i];
                    c = i;
                    // 出现次数最多的字符大于字符串长度的一半，必然会有相邻字符重复
                    if (maxCount > (s.length() + 1) / 2) {
                        return "";
                    }
                }
            }

            // 把出现次数最多的字符放在偶数位下标上
            char[] res = new char[s.length()];
            int index = 0;
            while (counts[c]-- > 0) {
                res[index] = (char) (c + 'a');
                index += 2;
            }

            // 其余字符放在其他位置
            for (int i = 0; i < counts.length; i++) {
                while (counts[i]-- > 0) {
                    if (index >= res.length) {
                        index = 1;
                    }
                    res[index] = (char) (i + 'a');
                    index += 2;
                }
            }
            return new String(res);
        }

    }

}
