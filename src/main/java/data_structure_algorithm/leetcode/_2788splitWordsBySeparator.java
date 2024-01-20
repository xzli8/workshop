package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _2788splitWordsBySeparator {

    public static class Solution1 {

        /**
         遍历拆分
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<String> splitWordsBySeparator(List<String> words, char separator) {
            List<String> res = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    if (c != separator) {
                        sb.append(c);
                    } else {
                        if (sb.length() > 0) {
                            res.add(sb.toString());
                            sb.setLength(0);
                        }
                    }
                }
                if (sb.length() > 0) {
                    res.add(sb.toString());
                    sb.setLength(0);
                }
            }
            return res;
        }

    }

}
