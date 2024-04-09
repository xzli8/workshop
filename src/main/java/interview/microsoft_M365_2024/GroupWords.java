package interview.microsoft_M365_2024;

import org.junit.Test;

import java.util.*;

public class GroupWords {

    // 2024.04.09

    /**
     * ["eat", "tea", "tan", "ate", "nat", "bat"]
     * ==>
     * [["ate", "eat", "tea"], ["nat", "tan"], ["bat"]]
     */

    @Test
    public void test() {
        List<String> strs1 = Arrays.asList("eat", "tea", "tan", "ate", "nat", "bat");
        System.out.println(group(strs1));

        List<String> strs2 = Arrays.asList("e", "tea", "tanf", "ate", "nfat", "at");
        System.out.println(group(strs2));

        List<String> strs3 = Arrays.asList("", "tea", "tanf", "ate", "nfat", "at");
        System.out.println(group(strs3));

        List<String> strs4 = Arrays.asList();
        System.out.println(group(strs4));

        List<String> strs5 = Arrays.asList("", "");
        System.out.println(group(strs5));

        List<String> strs6 = Arrays.asList("aabb", "bbaa", "aaab");
        System.out.println(group(strs6));
    }

    public List<List<String>> group(List<String> strs) {
        // group by word length
        Map<Integer, List<String>> len2Strs = new HashMap<>();
        for (String str : strs) {
            int len = str.length();
            len2Strs.putIfAbsent(len, new ArrayList<>());
            len2Strs.get(len).add(str);
        }

        // calculate each character of each word
        List<List<String>> res = new ArrayList<>();
        for (List<String> strings : len2Strs.values()) {
            Map<Long, List<String>> hash2Strs = new HashMap<>();
            for (String str : strings) {
                int[] counts = new int[26];
                for (char c : str.toCharArray()) {
                    counts[c - 'a']++;
                }
                long hash = hash(counts);
                hash2Strs.putIfAbsent(hash, new ArrayList<>());
                hash2Strs.get(hash).add(str);
            }
            for (List<String> stringList : hash2Strs.values()) {
                res.add(stringList);
            }
        }
        return res;
    }

    private long hash(int[] counts) {
        long sum = 0;
        for (int i = 0; i < 26; i++) {
            sum = sum * 26 + counts[i];
        }
        return sum;
    }

}
