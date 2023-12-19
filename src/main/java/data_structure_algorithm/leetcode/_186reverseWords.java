package data_structure_algorithm.leetcode;

public class _186reverseWords {

    /**
     * leetcode题解链接：https://www.cnblogs.com/mux1/p/6284592.html
     * lintcode：https://www.lintcode.com/problem/3648/
     */

    public static class Solution1 {

        /**
         *  双指针：先将字符串整体反转，然后将每个单词反转
         *      时间复杂度：O(N)
         *      空间复杂度：O(1)
         */
        public void reverseWords(char[] s) {
            int n = s.length;

            // 先整体反转字符串
            reverse(s, 0, n - 1);

            // 然后将每个单词反转
            int left = 0, right = 0;
            while (right < n) {
                if (s[right] == ' ') {
                    reverse(s, left, right - 1);
                    left = right + 1;
                }
                right++;
            }
            // 最后一个单词需要拿出来单独反转，因为结尾没有空格
            reverse(s, left, right - 1);
        }

        private void reverse(char[] s, int start, int end) {
            while (start < end) {
                char c = s[start];
                s[start] = s[end];
                s[end] = c;
                start++;
                end--;
            }
        }

    }

}
