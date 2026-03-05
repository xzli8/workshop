package data_structure_algorithm.leetcode;

public class _392isSubsequence {

    public static class Solution1 {

        /**
         双指针
         时间复杂度：O(M + N)
         空间复杂度：O(1)
         */
        public boolean isSubsequence(String s, String t) {
            int sl = s.length(), tl = t.length();
            int si = 0, ti = 0;
            while (si < sl && ti < tl) {
                if (s.charAt(si) == t.charAt(ti)) {
                    si++;
                }
                ti++;
            }
            return si == sl;
        }

    }



    public static class Solution2 {


        /**
         动态规划(编辑距离：与编辑距离相比比较简单，只有删除字符的情况，不用考虑替换和增加)
         */



    }



    public static class Solution3 {

        // 进阶：如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？

        /**
         动态规划(预处理主串): 考虑前面的双指针的做法，我们注意到我们有大量的时间用于在t中找到下一个匹配字符。这样我们可以预处理出对于t的每一个位置，从该位置开始往后每一个字符第一次出现的位置。
         */
        public boolean isSubsequence(String s, String t) {
            int n = s.length(), m = t.length();

            // 令 f[i][j] 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置
            int[][] f = new int[m + 1][26];
            for (int i = 0; i < 26; i++) {
                f[m][i] = m;
            }

            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < 26; j++) {
                    if (t.charAt(i) == j + 'a')
                        f[i][j] = i;
                    else
                        f[i][j] = f[i + 1][j];
                }
            }
            int add = 0;
            for (int i = 0; i < n; i++) {
                if (f[add][s.charAt(i) - 'a'] == m) {
                    return false;
                }
                add = f[add][s.charAt(i) - 'a'] + 1;
            }
            return true;
        }

    }



}
