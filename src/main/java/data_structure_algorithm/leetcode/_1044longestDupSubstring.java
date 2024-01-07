package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _1044longestDupSubstring {

    public static class Solution1 {

        /**
         二分查找 + 滑动哈希(RabinKarp)
         时间复杂度：O(NlogN)
         空间复杂度：O(N)
         */
        public String longestDupSubstring(String s) {
            // 找最长长度的重复子串 -> 找最后一个满足条件的解
            int n = s.length();
            int left = 1, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                String substr = dupSubstring(s, mid);
                if ("".equals(substr)) {
                    right = mid - 1;
                } else {
                    if (mid == n || "".equals(dupSubstring(s, mid + 1))) {
                        return substr;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            // 没有重复子串
            return "";
        }

        /**
         滑动哈希(RabinKarp)
         */
        private String dupSubstring(String s, int len) {
            // 计算pow
            // radix这里取一个比字符集大小26大的质数效果比较好(如果取26的话会有hash冲突导致出错)
            // 但即使这样仍有冲突的可能，更好的做法是用map代替set来处理哈希冲突，参考RabinKarp字符串匹配算法
            // 哈希冲突产生的来源：long范围不够会溢出，一旦溢出相当于对long的范围求余了，余数相同时有冲突
            long pow = 1, radix = 27, hash = 0;
            for (int i = 1; i < len; i++) {
                pow *= radix;
            }
            // 滑动哈希
            Set<Long> seen = new HashSet<>();
            int left = 0, right = 0;
            while (right < s.length()) {
                hash = hash * radix + s.charAt(right++) - 'a';
                if (right - left == len) {
                    if (seen.contains(hash)) {
                        return s.substring(left, right);
                    }
                    seen.add(hash);
                    hash = hash - (s.charAt(left++) - 'a') * pow;
                }
            }
            return "";
        }


        /**
         滑动哈希(RabinKarp)：用map代替set判断是否有哈希冲突
         这样做更严谨，但因为set判断是否重复会有较多耗时，所以超出时间限制，不能通过所有测试用例
         */
        // private String dupSubstring(String s, int len) {
        //     // 计算pow
        //     // radix这里取一个比字符集大小26大的质数效果比较好(如果取26的话会有hash冲突导致出错)
        //     // 但即使这样仍有冲突的可能，更好的做法是用map代替set来处理哈希冲突，参考RabinKarp字符串匹配算法
        //     // 哈希冲突产生的来源：long范围不够会溢出，一旦溢出相当于对long的范围求余了，余数相同时有冲突
        //     long pow = 1, radix = 26, hash = 0;
        //     for (int i = 1; i < len; i++) {
        //         pow *= radix;
        //     }
        //     // 滑动哈希(用map代替set处理哈希冲突)
        //     Map<Long, Set<String>> hash2Strs = new HashMap<>();
        //     int left = 0, right = 0;
        //     while (right < s.length()) {
        //         hash = hash * radix + s.charAt(right++) - 'a';
        //         if (right - left == len) {
        //             String substr = s.substring(left, right);
        //             if (hash2Strs.containsKey(hash) && hash2Strs.get(hash).contains(substr)) {
        //                 return substr;
        //             }
        //             if (!hash2Strs.containsKey(hash)) hash2Strs.put(hash, new HashSet<>());
        //             hash2Strs.get(hash).add(substr);
        //             hash = hash - (s.charAt(left++) - 'a') * pow;
        //         }
        //     }
        //     return "";
        // }

    }

}
