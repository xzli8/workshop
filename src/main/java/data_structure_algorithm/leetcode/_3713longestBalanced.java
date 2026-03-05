package data_structure_algorithm.leetcode;

public class _3713longestBalanced {

    public static class Solution1 {

        /**
         枚举+哈希表：O(N^2), O(N)
         */
        public int longestBalanced(String s) {
            int res = 0;
            for (int i = 0; i < s.length(); i++) {
                int[] counts = new int[26];
                next:
                for (int j = i; j < s.length(); j++) {
                    int cur = ++counts[s.charAt(j) - 'a'];
                    for (int count : counts) {
                        if (count > 0 && count != cur) {
                            continue next;
                        }
                    }
                    res = Math.max(res, j - i + 1);
                }
            }
            return res;
        }

    }

}
