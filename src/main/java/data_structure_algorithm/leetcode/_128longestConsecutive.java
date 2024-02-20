package data_structure_algorithm.leetcode;

import java.util.HashSet;
import java.util.Set;

public class _128longestConsecutive {

    public static class Solution0 {

        /**
         哈希表：https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestConsecutive(int[] nums) {
            Set<Integer> s = new HashSet<>();
            for (int num : nums) s.add(num);

            int maxLen = 0;
            for (int num : nums) {
                if (s.contains(num - 1)) continue;  // 跳过没必要的循环
                int cur = num, len = 1;
                while (s.contains(cur + 1)) {
                    cur += 1;
                    len += 1;
                }
                maxLen = Math.max(maxLen, len);
            }
            return maxLen;
        }

    }



    public static class Solution1 {

        /**
         桶计数 + 滑动窗口
         时间复杂度：O(N)
         空间复杂度：和nums数组中最大值和最小值的差值有关，测试用例会引起OOM
         */
         public int longestConsecutive(int[] nums) {
             if (nums.length == 0) return 0;

             // 桶计数
             int n = nums.length;
             int maxNum = nums[0], minNum = nums[0];
             for (int i = 1; i < n; i++) {
                 maxNum = Math.max(maxNum, nums[i]);
                 minNum = Math.min(minNum, nums[i]);
             }

             int m = maxNum - minNum + 1;
             int[] bucket = new int[m];  // 这一步会引发OOM
             for (int i = 0; i < n; i++) {
                 bucket[nums[i] - minNum] = 1;
             }

             // 滑动窗口
             int maxLen = 0;
             int left = 0, right = 0;
             while (left < m) {
                 while (bucket[left] == 0) {
                     left++;
                 }
                 right = left;
                 while (right < m && bucket[right] == 1) {
                     right++;
                 }
                 maxLen = Math.max(maxLen, right - left);
                 left = right;
             }
             return maxLen;
         }

    }



    public static class Solution2 {

        /**
         哈希表：https://leetcode.cn/problems/longest-consecutive-sequence/solutions/276931/zui-chang-lian-xu-xu-lie-by-leetcode-solution/?envType=study-plan-v2&envId=top-interview-150
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int longestConsecutive(int[] nums) {
            Set<Integer> numsSet = new HashSet<>();
            for (int num : nums) {
                numsSet.add(num);
            }

            int maxLen = 0;
            for (int num : numsSet) {
                // 跳过没必要的循环
                if (numsSet.contains(num - 1)) {
                    continue;
                }

                int currentNum = num;
                int currentLen = 1;
                while (numsSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentLen += 1;
                }
                maxLen = Math.max(maxLen, currentLen);
            }
            return maxLen;
        }

    }

}
