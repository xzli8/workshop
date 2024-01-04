package data_structure_algorithm.leetcode;

public class _28strStr {

    /**
     *  字符串匹配算法：BF、BM、KMP、RabinKarp...
     *      RabinKarp:https://blog.csdn.net/fdl123456/article/details/125551205
     */

    public static class Solution1 {

        /**
         BF(暴力匹配)
         时间复杂度：O(N*M)
         空间复杂度：O(1)
         */
         public int strStr(String haystack, String needle) {
             int n = haystack.length(), m = needle.length();
             for (int i = 0; i + m <= n; i++) {
                 if (needle.equals(haystack.substring(i, i + m))) {
                     return i;
                 }
             }
             return -1;
         }

    }



    public static class Solution2 {

        /**
         RabinKarp(滑动哈希)
         NOTE：这里没有显示取余，因为溢出相当于对long范围进行取余，所以是一种隐式取余。
         取余会导致哈希冲突，所以在哈希值相等时，还需要判断字符串是否相等。

         时间复杂度：O(N)，与子串长度无关
         空间复杂度：O(1)
         */
        public int strStr(String s, String p) {
            // 计算pow
            int n = s.length(), m = p.length(), radix = 26;
            long pow = 1;
            for (int i = 1; i < m; i++) pow *= radix;
            // 计算模式串哈希值
            long  patternHash = 0;
            for (int i = 0; i < m; i++) {
                patternHash = patternHash * radix + p.charAt(i) - 'a';
            }
            // 滑动哈希
            int left = 0, right = 0;
            long windowHash = 0;
            while (right < n) {
                windowHash = windowHash * radix + s.charAt(right++) - 'a';
                if (right - left == m) {
                    if (windowHash == patternHash && s.substring(left, right).equals(p)) {
                        return left;
                    }
                    windowHash -= (s.charAt(left++) - 'a') * pow;
                }
            }
            return -1;
        }

    }



    public static class Solution3 {

        /**
         RabinKarp(滑动哈希)
         时间复杂度：O(N)，与子串长度无关
         空间复杂度：O(1)
         */
        public int strStr(String haystack, String needle) {
            int n = haystack.length(), m = needle.length();

            // 26进制(仅由小写英文字符组成)
            int radix = 26;
            // 取一个较大的质数作为求模的余数，防止溢出
            long prime = 1658598167;
            // 计算哈希因子
            long factor = 1;
            for (int i = 0; i < m - 1; i++) {
                factor = (factor * radix) % prime;
            }

            // 计算模式串哈希值
            long patternHash = 0;
            for (int i = 0; i < m; i++) {
                patternHash = (patternHash * radix % prime + needle.charAt(i) - 'a') % prime;
            }

            // 滑动哈希
            int left = 0, right = 0;
            long windowHash = 0;
            while (right < n) {
                // 扩大窗口，移入字符，计算哈希
                windowHash = (windowHash * radix % prime + haystack.charAt(right++) - 'a')
                        % prime;

                // 当字符串长度达到模式串长度时
                if (right - left == m) {
                    // 当哈希值相同时
                    if (windowHash == patternHash) {
                        // 进一步确认模式串是否匹配，防止哈希冲突
                        if (needle.equals(haystack.substring(left, right))) {
                            return left;
                        }
                    }

                    // 缩小窗口，移出字符，计算哈希(多加一个prime防止出现负数)
                    windowHash = (windowHash - (haystack.charAt(left++) - 'a') * factor
                            % prime + prime) % prime;
                }
            }
            return -1;
        }

    }


}
