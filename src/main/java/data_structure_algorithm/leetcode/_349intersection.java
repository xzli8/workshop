package data_structure_algorithm.leetcode;

import java.util.*;

public class _349intersection {

    public static class Solution1 {

        /**
         哈希表
         时间复杂度：O(M+N)
         空间复杂度：O(M+N)
         */
         public int[] intersection(int[] nums1, int[] nums2) {
             // 将nums1中的元素都加入集合
             Set<Integer> s1 = new HashSet<>();
             for (int num : nums1) {
                 s1.add(num);
             }

             // 遍历nums2中的元素，如果该元素在集合中出现，则加入新集合(新集合去重)
             Set<Integer> s2 = new HashSet<>();
             for (int num : nums2) {
                 if (s1.contains(num)) {
                     s2.add(num);
                 }
             }

             // 将新集合转换成数组返回
             return s2.stream().mapToInt(Integer::intValue).toArray();
         }

    }

    public static class Solution2 {

        /**
         排序 + 双指针
         时间复杂度：O(MlogM + NlogN)，排序
         空间复杂度：O(logM + logN)，排序
         */
        public int[] intersection(int[] nums1, int[] nums2) {
            // 排序
            Arrays.sort(nums1);
            Arrays.sort(nums2);

            // 双指针
            List<Integer> res = new ArrayList<>();
            int idx1 = 0, idx2 = 0;
            while (idx1 < nums1.length && idx2 < nums2.length) {
                int num1 = nums1[idx1], num2 = nums2[idx2];
                if (num1 == num2) {
                    if (res.size() == 0 || res.get(res.size() - 1) != num1) {
                        res.add(num1);
                    }
                    idx1++;
                    idx2++;
                } else if (num1 < num2) {
                    idx1++;
                } else {
                    idx2++;
                }
            }

            // 结果转换成int[]返回
            return res.stream().mapToInt(Integer::intValue).toArray();
        }

    }

}
