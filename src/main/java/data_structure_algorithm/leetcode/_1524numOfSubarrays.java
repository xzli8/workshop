package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _1524numOfSubarrays {

    public static class Solution1 {

        /**
         前缀和 + 哈希表
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int numOfSubarrays(int[] arr) {
            int preSum = 0, count = 0, MOD = (int) (1e9) + 7;
            Map<Boolean, Integer> odd2Count = new HashMap<>();
            odd2Count.put(true, 0);
            odd2Count.put(false, 1);
            for (int num : arr) {
                preSum += num;
                if ((preSum & 1) == 1) {
                    count += odd2Count.get(false);
                    if (count >= MOD) count -= MOD;
                    int oddCount = odd2Count.get(true) + 1;
                    if (oddCount >= MOD) oddCount -= MOD;
                    odd2Count.put(true, oddCount);
                } else {
                    count += odd2Count.get(true);
                    if (count > MOD) count -= MOD;
                    int evenCount = odd2Count.get(false) + 1;
                    if (evenCount >= MOD) evenCount -= MOD;
                    odd2Count.put(false, evenCount);
                }
            }
            return count;
        }

    }

}
