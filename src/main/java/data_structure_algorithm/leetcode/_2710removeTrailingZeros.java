package data_structure_algorithm.leetcode;

public class _2710removeTrailingZeros {

    public static class Solution1 {

        /**
         用一个指针指向字符串末尾，开始往前遍历，找到第一个不为0的停下，返回substring
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String removeTrailingZeros(String num) {
            int n = num.length() - 1;
            while (n > 0 && num.charAt(n) == '0') {
                n--;
            }
            return num.substring(0, n + 1);
        }

    }

}
