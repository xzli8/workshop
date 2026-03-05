package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _2404mostFrequentEven {

    public static class Solution1 {

        /**
         哈希表: O(N), O(N)
         */
         public int mostFrequentEven(int[] nums) {
             // 用哈希表统计每个数字出现的次数
             Map<Integer, Integer> num2Count = new HashMap<>();
             for (int num : nums) {
                 num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
             }

             // 遍历哈希表，计算最大出现次数及其对应数字
             int maxCount = Integer.MIN_VALUE;
             List<Integer> maxNums = new ArrayList<>();
             for (int num : num2Count.keySet()) {
                 if (num % 2 != 0) continue;
                 int count = num2Count.get(num);
                 if (maxCount < count) {
                     maxCount = count;
                     maxNums.clear();
                     maxNums.add(num);
                 } else if (maxCount == count) {
                     maxNums.add(num);
                 }
             }

             // 遍历求最小
             if (maxNums.size() == 0) return -1;
             if (maxNums.size() == 1) return maxNums.get(0);
             int min = maxNums.get(0);
             for (int num : maxNums) {
                 min = Math.min(min, num);
             }
             return min;
         }


        /**
         哈希表(一次遍历): O(N), O(N)
         */
        public int mostFrequentEvenII(int[] nums) {
            int res = -1;
            Map<Integer, Integer> num2Count = new HashMap<>();
            for (int num : nums) {
                if (num % 2 != 0) continue;
                int count = num2Count.merge(num, 1, Integer::sum), resCount = num2Count.getOrDefault(res, 0);
                if (res < 0 || count > resCount || count == resCount && num < res) res = num;
            }
            return res;
        }

    }

}
