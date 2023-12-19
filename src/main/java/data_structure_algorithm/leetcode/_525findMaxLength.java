package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _525findMaxLength {

    public static class Solution1 {

        /**
         暴力枚举：超时
         时间复杂度：O(N^3)
         空间复杂度：O(1)
         */
         public int findMaxLength(int[] nums) {
             int n = nums.length;
             int maxLen = 0;
             // 枚举区间起点
             for (int i = 0; i < n; i++) {
                 // 枚举区间终点(这里从大到小枚举，如果较大的满足了就可以直接退出内层循环)
                 for (int j = n - 1; j > i; j--) {
                     // 计算区间内0和1的数量
                     int count0 = 0, count1 = 0;
                     for (int k = i; k <= j; k++) {
                         if (nums[k] == 0) {
                             count0++;
                         } else if (nums[k] == 1){
                             count1++;
                         }
                     }

                     // 如果区间内0和1的数量相等，更新最大长度
                     if (count0 == count1) {
                         maxLen = Math.max(maxLen, j - i + 1);
                         break;  // 较大的满足了，直接退出内层循环
                     }
                 }
             }
             return maxLen;
         }

    }



    public static class Solution2 {

        /**
         前缀和：(超时)用空间换时间，适用于数组不仅含有0和1的普遍情况
         时间复杂度：O(N^2)
         空间复杂度：O(N)
         */
         public int findMaxLength(int[] nums) {
             // 计算前缀和数组，preSum0[i]表示前i个元素中0的个数，preSum1[i]表示前i个元素中1的个数
             int n = nums.length;
             int[] preSum0 = new int[n+1], preSum1 = new int[n+1];
             preSum0[0] = 0;
             preSum1[0] = 0;
             for (int i = 1; i <= n; i++) {
                 preSum0[i] = preSum0[i-1] + (nums[i-1] == 0 ? 1 : 0);
                 preSum1[i] = preSum1[i-1] + (nums[i-1] == 1 ? 1 : 0);
             }

             int maxLen = 0;
             // 枚举(前缀和)区间起点
             for (int i = 0; i <= n; i++) {
                 // 枚举(前缀和)区间终点(这里从大到小枚举，这样如果较大的区间满足条件可以直接跳出内层循环)
                 for (int j = n; j > i; j--) {
                     // 如果区间内0和1的数量相等，更新最大长度
                     if (preSum0[j] - preSum0[i] == preSum1[j] - preSum1[i]) {
                         maxLen = Math.max(maxLen, j - i);   // 这里使用前缀和作差，所以长度不需要-1
                         break;  // 跳出内层循环
                     }
                 }
             }
             return maxLen;
         }

    }



    public static class Solution3 {

        /**
         前缀和 + 哈希表
         因为数组只含有0和1，所以可以继续简化。用sum表示遍历过所有元素的和(0表示-1，1表示1)，
         用哈希表记录sum与index的对应关系，如果遍历到某个元素时的sum，与前面元素的sum相等，即map.contains(sum)，
         说明这段区间内0和1的数目相等，计算区间长度。

         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int findMaxLength(int[] nums) {
            // 前缀和映射，key是前缀和，value是数组下标
            Map<Integer, Integer> sum2Index = new HashMap<>();

            // 初始化前缀和(前缀和为0时，下标为-1)
            sum2Index.put(0, -1);

            // 遍历数组
            int maxLen = 0;
            for (int i = 0, sum = 0; i < nums.length; i++) {
                sum += nums[i] == 0 ? -1 : 1;
                if (sum2Index.containsKey(sum)) {
                    // 这里计算长度时不需要-1，因为当前的sum等于之前的sum，表示的是当前元素和之前元素之间的0和1数量相等
                    maxLen = Math.max(maxLen, i - sum2Index.get(sum));
                    // NOTE：这里不需要更新kv，因为要计算的是最长长度，只需要sum最早出现时对应的index即可
                }
                else {
                    sum2Index.put(sum, i);
                }
            }
            return maxLen;
        }


    }





}
