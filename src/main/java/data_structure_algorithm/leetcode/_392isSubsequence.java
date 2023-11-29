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
         动态规划(预处理主串)
         */



    }



}
