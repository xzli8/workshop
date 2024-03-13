package data_structure_algorithm.leetcode;

public class _2864maximumOddBinaryNumber {

    public static class Solution1 {

        /**
         贪心：将最后一位放一个1，其余的1放在最前面
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public String maximumOddBinaryNumber(String s) {
            char[] cs = s.toCharArray();
            int n = s.length(), i = n - 1;

            // 最后一位放一个1
            while (i >= 0 && cs[i] != '1') i--;
            swap(cs, i, n - 1);

            // 其余的1放在最前面
            int j = 0;
            i--;
            while (j < i) {
                while (j < i && cs[i] != '1') i--;
                swap(cs, i, j++);
            }
            return String.valueOf(cs);
        }

        private void swap(char[] cs, int i, int j) {
            char c = cs[i];
            cs[i] = cs[j];
            cs[j] = c;
        }

    }

}
