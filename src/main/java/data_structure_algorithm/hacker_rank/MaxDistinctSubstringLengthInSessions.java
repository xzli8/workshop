package data_structure_algorithm.hacker_rank;

import java.util.HashSet;
import java.util.Set;

public class MaxDistinctSubstringLengthInSessions {

    public static void main(String[] args) {
        System.out.println(maxDistinctSubstringLengthInSessions("abcabcbb"));
        System.out.println(maxDistinctSubstringLengthInSessions("*"));
        System.out.println(maxDistinctSubstringLengthInSessions("ab*bca"));
    }

    public static int maxDistinctSubstringLengthInSessions(String sessionString) {
        int maxLen = 0;
        for (String s : sessionString.split("\\*")) {
            maxLen = Math.max(maxLen, maxDistinctSubstringLengthOfString(s));
        }
        return maxLen;
    }

    private static int maxDistinctSubstringLengthOfString(String s) {
        int n = s.length(), l = 0, r = 0, maxLen = 1;
        Set<Character> set = new HashSet<>();
        while (r < n) {
            while (r < n && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                r++;
            }
            maxLen = Math.max(maxLen, r - l);
            while (l < r && r < n && set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }
        }
        return maxLen;
    }

}
