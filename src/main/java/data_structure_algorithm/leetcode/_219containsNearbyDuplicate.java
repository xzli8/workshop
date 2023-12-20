package data_structure_algorithm.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _219containsNearbyDuplicate {

    public static class Solution1 {

        /**
         哈希表
         思路：用哈希表记录元素及其最后出现的下标
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public boolean containsNearbyDuplicate(int[] nums, int k) {
             Map<Integer, Integer> num2Idx = new HashMap<>();
             for (int i = 0; i < nums.length; i++) {
                 if (num2Idx.containsKey(nums[i])) {
                     if (i - num2Idx.get(nums[i]) <= k) {
                         return true;
                     }
                 }
                 num2Idx.put(nums[i], i);
             }
             return false;
         }

    }



    public static class Solution2 {

        /**
         滑动窗口
         思路：维持窗口大小为k，当要加入右指针对应的元素时，需要首先判断窗口大小，如果窗口大小大于k，
         需要首先移除左指针对应的元素，然后再加入右指针对应的元素
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            int left = 0, right = 0;
            Set<Integer> set = new HashSet<>();
            while (right < nums.length) {
                if (right - left > k) {
                    set.remove(nums[left++]);
                }
                if (!set.add(nums[right++])) {
                    return true;
                }
            }
            return false;
        }

    }


}
