package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _187findRepeatedDnaSequences {

    public static class Solution1 {

        /**
         暴力解法(时间复杂度与子串长度有关)
         时间复杂度：O(N*L), N为字符串s长度，L为子串长度
         空间复杂度：O(N*L)
         */
         public List<String> findRepeatedDnaSequences(String s) {

             // 记录出现过的子串
             Set<String> seen = new HashSet<>();

             // 记录多次重复出现的子串
             Set<String> res = new HashSet<>();

             // 遍历
             for (int i = 0; i + 10 <= s.length(); i++) {
                 String subStr = s.substring(i, i + 10);
                 if (seen.contains(subStr)) {
                     res.add(subStr);
                 } else {
                     seen.add(subStr);
                 }
             }
             return new ArrayList<>(res);
         }

    }



    public static class Solution2 {

        /**
         RabinKarp(滑动哈希)
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<String> findRepeatedDnaSequences(String s) {
            // 将字符串转换为四进制数组
            int n = s.length();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                switch (s.charAt(i)) {
                    case 'A' : nums[i] = 0; break;
                    case 'C' : nums[i] = 1; break;
                    case 'G' : nums[i] = 2; break;
                    case 'T' : nums[i] = 3; break;
                }
            }

            // 记录出现过的字符串的哈希值
            Set<Integer> seen = new HashSet<>();
            // 重复出现的字符串
            Set<String> res = new HashSet<>();

            // 滑动哈希
            int left = 0, right = 0;
            int windowHash = 0, len = 10, radix = 4, factor = (int) Math.pow(radix, len - 1);
            while (right < n) {
                // 扩大窗口，移入字符，计算哈希值
                windowHash = windowHash * radix + nums[right++];

                // 子串长度达到要求
                if (right - left == len) {
                    if (seen.contains(windowHash)) {
                        res.add(s.substring(left, right));
                    } else {
                        seen.add(windowHash);
                    }

                    // 缩小窗口，移除字符，重新计算哈希值
                    windowHash = windowHash - nums[left++] * factor;
                }
            }
            return new ArrayList<>(res);
        }

    }


}
