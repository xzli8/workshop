package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15threeSum {

    public static class Solution1 {

        /**
         排序+双指针（注意重复元素）
         时间复杂度：O(NlogN)
         空间复杂度：O(1)
         */
        public List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (i > 0 && nums[i] == nums[i-1]) continue;
                int left = i+1;
                int right = nums.length-1;
                while (left < right) {
                    int tmp = nums[i] + nums[left] + nums[right];
                    if (tmp == 0) {
                        res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // 去除重复元素
                        while (left < right && nums[left] == nums[left+1]) left++;
                        while (left < right && nums[right] == nums[right-1]) right--;
                        left++;
                        right--;
                    } else if (tmp < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            return res;
        }

    }

}
