package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _523checkSubarraySum {

    public static class Solution1 {

        /**
         前缀和：https://leetcode.cn/problems/continuous-subarray-sum/solutions/808246/gong-shui-san-xie-tuo-zhan-wei-qiu-fang-1juse/
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            // 预处理前缀和数组
            int n = nums.length;
            int[] preSum = new int[n+1];
            for (int i = 1; i <= n; i++) {
                preSum[i] = preSum[i-1] + nums[i-1];
            }

            // key-前缀和对k取余；value-前缀和对应的下标
            Map<Integer, Integer> map = new HashMap<>();

            // 遍历前缀和数组，判断当前元素对k取余是否和前面元素对k取余相等
            for (int i = 0; i <= n; i++) {
                int mod = preSum[i] % k;
                // 当余数相等时，说明这个子数组满足条件
                if (map.containsKey(mod)) {
                    if (i - map.get(mod) >= 2) return true;
                }
                // 这里需要记录mod对应的第一个下标，后续不要更新了(eg:[5, 0, 0, 0, 0] 3)
                else {
                    map.put(mod, i);
                }
            }
            return false;
        }

    }

}
