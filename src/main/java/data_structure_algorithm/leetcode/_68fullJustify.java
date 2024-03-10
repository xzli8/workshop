package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _68fullJustify {

    public static class Solution1 {

        /**
         模拟
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> res = new ArrayList<>();
            int n = words.length, i = 0;
            while (i < n) {
                // 计算单词数量
                int width = 0, start = i, wordCount = 1;
                while (i < n) {
                    width += words[i].length();
                    if (width + wordCount - 1 > maxWidth) {
                        width -= words[i].length();
                        break;
                    }
                    i++;
                    wordCount++;
                }
                // 拼接每一行
                StringBuilder sb = new StringBuilder();
                if (i < n) {
                    // 计算总空格数量
                    int spaceCount = wordCount - 2;
                    if (spaceCount > 0) {
                        // 计算每个单词之间的空格数量
                        int[] spaces = new int[spaceCount];
                        Arrays.fill(spaces, (maxWidth - width) / spaceCount);
                        for (int j = 0; j < (maxWidth - width) % spaceCount; j++) {
                            spaces[j]++;
                        }
                        // 单词间插入空格
                        for (int j = start; j < i - 1; j++) {
                            sb.append(words[j]);
                            for (int k = 0; k < spaces[j - start]; k++) {
                                sb.append(" ");
                            }
                        }
                        sb.append(words[i - 1]);
                    } else {
                        // 这一行只有一个单词
                        sb.append(words[start]);
                        for (int j = 0; j < maxWidth - words[start].length(); j++) sb.append(" ");
                    }
                } else {
                    // 最后一行单独处理
                    for (int j = start; j < i - 1; j++) {
                        sb.append(words[j]).append(" ");
                    }
                    sb.append(words[i - 1]);
                    for (int j = sb.length(); j < maxWidth; j++) {
                        sb.append(" ");
                    }
                }
                res.add(sb.toString());
            }
            return res;
        }

    }

}
