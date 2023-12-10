package data_structure_algorithm;

import java.util.HashMap;
import java.util.Map;

public class _387firstUniqChar {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int firstUniqChar(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for (char c : s.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            for (int i = 0; i < s.length(); i++) {
                if (map.get(s.charAt(i)) == 1) {
                    return i;
                }
            }
            return -1;
        }

    }



    public static class Solution2 {

        /**
         s只包含小写字母，用数组替代哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int firstUniqChar(String s) {
             int[] char2count = new int[26];
             for (char c : s.toCharArray()) {
                 char2count[c - 'a']++;
             }

             for (int i = 0; i < s.length(); i++) {
                 if (char2count[s.charAt(i) - 'a'] == 1) {
                     return i;
                 }
             }
             return -1;
         }

    }


}
