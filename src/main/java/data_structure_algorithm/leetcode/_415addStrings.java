package data_structure_algorithm.leetcode;

public class _415addStrings {

    public static class Solution1 {

        /**
         模拟竖式加法
         时间复杂度：O(max(len1, len2))
         空间复杂度：O(1)
         */
        public String addStrings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, add = 0;
            StringBuffer ans = new StringBuffer();
            while (i >= 0 || j >= 0 || add != 0) {
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int result = x + y + add;
                ans.append(result % 10);
                add = result / 10;
                i--;
                j--;
            }
            ans.reverse();
            return ans.toString();
        }

    }

}
