package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _205isIsomorphic {

    public static class Solution1 {

        /**
         哈希表：建立双向映射
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> s2t = new HashMap<>(), t2s = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char cs = s.charAt(i), ct = t.charAt(i);
                if (s2t.containsKey(cs) && !s2t.get(cs).equals(ct)) return false;
                s2t.putIfAbsent(cs, ct);
                if (t2s.containsKey(ct) && !t2s.get(ct).equals(cs)) return false;
                t2s.putIfAbsent(ct, cs);
            }
            return true;
        }

    }

}
