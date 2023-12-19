package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _974subarraysDivByK {

    public static class Solution1 {

        /**
         前缀和：超时
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int subarraysDivByK(int[] nums, int k) {
             int n = nums.length;
             int[] preSum = new int[n + 1];
             for (int i = 1; i <= n; i++) {
                 preSum[i] = preSum[i - 1] + nums[i - 1];
             }

             int count = 0;
             for (int i = 0; i <= n; i++) {
                 for (int j = i + 1; j <= n; j++) {
                     if ((preSum[j] - preSum[i]) % k == 0) {
                         count++;
                     }
                 }
             }
             return count;
         }

    }



    public static class Solution2 {


        /**
         前缀和 + 哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int subarraysDivByK(int[] nums, int k) {
            // key:区间和除以k的余数, value:数量
            Map<Integer, Integer> mod2Count = new HashMap<>();
            mod2Count.put(0, 1);

            int preSum = 0, count = 0;
            for (int i = 0; i < nums.length; i++) {
                // 计算前缀和
                preSum += nums[i];

                // 当被除数为负数时，取模结果为负数，需要进行修正
                int mod = (preSum % k + k) % k;

                // 前缀和除以k的余数相同，说明相减能被k整除
                if (mod2Count.containsKey(mod)) {
                    count += mod2Count.get(mod);
                }
                mod2Count.put(mod, mod2Count.getOrDefault(mod, 0) + 1);
            }
            return count;
        }

    }


}
