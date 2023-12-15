package data_structure_algorithm.leetcode;

public class _557reverseWords {

    public static class Solution1 {

        /**
         开辟额外空间
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public String reverseWords(String s) {
             String[] strs = s.split(" ");
             for (int i = 0; i < strs.length; i++) {
                 StringBuilder sb = new StringBuilder(strs[i]);
                 strs[i] = sb.reverse().toString();
             }
             return String.join(" ", strs);
         }

    }



    public static class Solution2 {

        /**
         原地交换(对java不适用，因为java中String是不可变类型，但可以写一写思路)
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public String reverseWords(String s) {
            char[] cs = s.toCharArray();
            int n = s.length(), i = 0;
            while (i < n) {
                int start = i;
                while (i < n && cs[i] != ' ') {
                    i++;
                }

                int left = start, right = i - 1;
                while (left < right) {
                    char c = cs[left];
                    cs[left] = cs[right];
                    cs[right] = c;
                    left++;
                    right--;
                }

                while (i < n && cs[i] == ' ') {
                    i++;
                }
            }
            return String.valueOf(cs);
        }

    }



}
