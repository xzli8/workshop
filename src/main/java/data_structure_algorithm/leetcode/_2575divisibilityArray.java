package data_structure_algorithm.leetcode;

public class _2575divisibilityArray {

    public static class Solution1 {

        /**
         模拟:数据范围超限
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
         public int[] divisibilityArray(String word, int m) {
             int n = word.length();
             int[] div = new int[n];
             long num = 0;
             int i = 0;
             for (char c : word.toCharArray()) {
                 num = num * 10 + c - '0';
                 if (num % m == 0) div[i++] = 1;
                 else div[i++] = 0;
             }
             return div;
         }

    }



    public static class Solution2 {

        /**
         模拟：(a + b) % m = (a % m + b % m) % m，可以在计算过程中取模，而不是最后才取模，防止范围溢出
         ref:https://leetcode.cn/problems/find-the-divisibility-array-of-a-string/solutions/2134227/cong-zuo-dao-you-ji-suan-by-endlesscheng-ywls/?envType=daily-question&envId=2024-03-07
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[] divisibilityArray(String word, int m) {
            int n = word.length();
            int[] div = new int[n];
            long mod = 0;
            for (int i = 0; i < n; i++) {
                mod = (mod * 10 + word.charAt(i) - '0') % m;
                if (mod == 0) div[i] = 1;
            }
            return div;
        }

    }

}
