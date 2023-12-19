package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _523checkSubarraySum {

    public static class Solution1 {

        /**
         前缀和：若a % k = b % k = mod，那么(a - b) % k = mod
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            // 记录前缀和对k取余的结果到最小下标的映射
            Map<Integer, Integer> preSumModK2Idx = new HashMap<>();
            preSumModK2Idx.put(0, -1);

            // 遍历数组元素，一边计算前缀和，一边更新映射
            int preSum = 0;
            for (int i = 0; i < nums.length; i++) {
                preSum += nums[i];
                int mod = preSum % k;
                if (preSumModK2Idx.containsKey(mod)) {
                    if (i - preSumModK2Idx.get(mod) > 1) return true;
                } else {
                    preSumModK2Idx.put(mod, i);
                }
            }
            return false;
        }

    }

}
