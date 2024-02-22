package data_structure_algorithm.leetcode;

public class _38countAndSay {

    public static class Solution1 {

        /**
         模拟：递归
         时间复杂度：O(N * M)
         空间复杂度：O(N * M)
         */
         public String countAndSay(int n) {
             if (n == 1) return "1";
             String s = countAndSay(n - 1);

             StringBuilder sb = new StringBuilder();
             int i = 0;
             while (i < s.length()) {
                 int j = i + 1;
                 while (j < s.length() && s.charAt(j) == s.charAt(i)) j++;
                 sb.append(j - i).append(s.charAt(i));
                 i = j;
             }
             return sb.toString();
         }

    }



    public static class Solution2 {

        /**
         模拟：递推
         时间复杂度：O(N * M)
         空间复杂度：O(M)，省去了递归调用栈的开销
         */
        public String countAndSay(int n) {
            String s = "1";
            for (int i = 2; i <= n; i++) {
                StringBuilder sb = new StringBuilder();
                int start = 0;
                while (start < s.length()) {
                    int end = start + 1;
                    while (end < s.length() && s.charAt(end) == s.charAt(start)) end++;
                    sb.append(end - start).append(s.charAt(start));
                    start = end;
                }
                s = sb.toString();
            }
            return s;
        }

    }

}
