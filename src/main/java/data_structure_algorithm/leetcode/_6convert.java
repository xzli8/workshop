package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _6convert {

    public static class Solution1 {

        /**
         模拟(按行模拟)
         ref：https://leetcode.cn/problems/zigzag-conversion/solutions/21610/zzi-xing-bian-huan-by-jyd/)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String convert(String s, int numRows) {
            if (numRows == 1) return s;

            // rows[i]用于记录每一行的结果
            List<StringBuilder> rows = new ArrayList<>(numRows);
            for (int i = 0; i < numRows; i++) {
                rows.add(new StringBuilder());
            }

            // 遍历字符串s，flag用于控制行索引的变化
            int i = 0, flag = -1;
            for (char c : s.toCharArray()) {
                rows.get(i).append(c);
                if (i == 0 || i == numRows - 1) flag = -flag;
                i += flag;
            }

            // 最终结果
            StringBuilder res = new StringBuilder();
            for (StringBuilder row : rows) {
                res.append(row);
            }
            return res.toString();
        }

    }

}
