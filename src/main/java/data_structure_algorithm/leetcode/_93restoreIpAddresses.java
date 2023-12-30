package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _93restoreIpAddresses {

    public static class Solution1 {

        /**
         回溯
         时间复杂度：
         空间复杂度：
         */
        public List<String> restoreIpAddresses(String s) {
            backtrace(s, 0, 0, new ArrayList<>());
            return res;
        }

        private List<String> res = new ArrayList<>();
        private void backtrace(String s, int dotNum, int startIndex, List<String> path) {
            if (dotNum == 3) {
                if (isValid(s, startIndex, s.length() - 1)) {
                    path.add(s.substring(startIndex, s.length()));
                    res.add(String.join(".", path));
                    path.remove(path.size() - 1);
                }
                return;
            }

            for (int i = startIndex; i < s.length(); i++) {
                if (isValid(s, startIndex, i)) {
                    path.add(s.substring(startIndex, i + 1));
                    backtrace(s, dotNum + 1, i + 1, path);
                    path.remove(path.size() - 1);
                } else {
                    break;  // 剪枝：往后不可能有可行解
                }
            }
        }

        private boolean isValid(String s, int left, int right) {
            if (left > right) return false;

            // 以0开头的是非法解
            if (s.charAt(left) == '0' && left != right) return false;

            // 不能超过255
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum = sum * 10 + s.charAt(i) - '0';
                if (sum > 255) return false;
            }
            return true;
        }

    }

}
