package data_structure_algorithm.leetcode;

import java.util.*;

public class _49groupAnagrams {


    public static class Solution0 {

        /**
         哈希表：统计每个字符串中各个字符出现次数，通过次数数组计算出一个唯一的哈希值作为key，相应的字符串作为value
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                int[] counts = new int[26];
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    counts[str.charAt(i) - 'a']++;
                }
                // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键(将出现次数为0的字母编码也可以，但效率差点儿)
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < 26; i++) {
                    if (counts[i] != 0) {
                        sb.append((char) ('a' + i));
                        sb.append(counts[i]);
                    }
                }
                String key = sb.toString();
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                list.add(str);
                map.put(key, list);
            }
            return new ArrayList<>(map.values());
        }

    }



    public static class Solution1 {

        /**
         哈希表：(之前能通过，现在过不了～)
         思路：统计每个字符串中各字符出现的次数，然后计算出该hash表的一个唯一值(hashcode)作为key，相应字符串的集合作为value
         关键点：统计次数的hash表counts的hashcode如何计算？不能直接用int[] counts作为key，因为java会把该引用的地址作为key，导致每次key都是一样的也不能直接拼接成字符串组装，反例：["bdddddddddd","bbbbbbbbbbc"]
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public List<List<String>> groupAnagrams(String[] strs) {
            int[] counts = new int[26];
            Map<Long, List<String>> map = new HashMap<>();
            for (String s : strs) {
                Arrays.fill(counts, 0);
                for (char c : s.toCharArray()) {
                    counts[c - 'a']++;
                }

                long key = counts2Key(counts);
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                List<String> list = map.get(key);
                list.add(s);
                map.put(key, list);
            }
            return new ArrayList<>(map.values());
        }

        private long counts2Key(int[] counts) {
            int sum = 0, factor = 1;
            for (int i = 0; i < 26; i++) {
                sum += counts[i] * factor;
                factor *= 26;
            }
            return sum;
        }

    }

}
