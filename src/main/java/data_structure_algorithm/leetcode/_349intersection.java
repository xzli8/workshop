package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _349intersection {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(M+N)
         空间复杂度：O(M+N)
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            for (int num : nums1) {
                set1.add(num);
            }
            Set<Integer> set2 = new HashSet<>();
            for (int num : nums2) {
                set2.add(num);
            }
            return getIntersection(set1, set2);
        }

        private int[] getIntersection(Set<Integer> s1, Set<Integer> s2) {
            if (s1.size() > s2.size()) {
                return getIntersection(s2, s1);
            }

            Set<Integer> intersectionSet = new HashSet<>();
            for (int num : s1) {
                if (s2.contains(num)) {
                    intersectionSet.add(num);
                }
            }

            int[] res = new int[intersectionSet.size()];
            int idx = 0;
            for (int num : intersectionSet) {
                res[idx++] = num;
            }
            return res;
        }

    }

}
