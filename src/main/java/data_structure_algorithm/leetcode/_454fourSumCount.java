package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _454fourSumCount {

    public static class Solution1 {

        /**
         暴力搜索
         时间复杂度：O(N^4)
         空间复杂度：O(1)
         */
         public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
             int count = 0;
             for (int n1 : nums1) {
                 for (int n2 : nums2) {
                     for (int n3 : nums3) {
                         for (int n4 : nums4) {
                             if (n1 + n2 + n3 + n4 == 0) {
                                 count++;
                             }
                         }
                     }
                 }
             }
             return count;
         }

    }



    public static class Solution2 {

        /**
         哈希表:(类似题："1.两数之和")
         时间复杂度：O(N^3)
         空间复杂度：O(N)
         */
         public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
             // 计算nums1[i]的值及其出现次数
             Map<Integer, Integer> num2Count = new HashMap<>();
             for (int n1 : nums1) {
                 num2Count.put(n1, num2Count.getOrDefault(n1, 0) + 1);
             }

             // 遍历nums2, nums3, nums4，统计次数
             int count = 0;
             for (int n2 : nums2) {
                 for (int n3 : nums3) {
                     for (int n4 : nums4) {
                         int target = n2 + n3 + n4;
                         count += num2Count.getOrDefault(-target, 0);
                     }
                 }
             }
             return count;
         }

    }



    public static class Solution3 {

        /**
         哈希表:(类似题："1.两数之和")
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
        public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            // 计算nums1[i] + nums2[j]的值及其出现次数
            Map<Integer, Integer> sum2Count = new HashMap<>();
            for (int n1 : nums1) {
                for (int n2 : nums2) {
                    int sum = n1 + n2;
                    sum2Count.put(sum, sum2Count.getOrDefault(sum, 0) + 1);
                }
            }

            // 遍历nums3[i] + nums4[j]，统计次数
            int count = 0;
            for (int n3 : nums3) {
                for (int n4 : nums4) {
                    int sum = n3 + n4;
                    count += sum2Count.getOrDefault(-sum, 0);
                }
            }
            return count;
        }

    }



}
