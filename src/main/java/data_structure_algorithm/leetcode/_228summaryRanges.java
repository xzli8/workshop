package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _228summaryRanges {

    public static class Solution1 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<>();
            int left = 0, right = 1;
            while (right <= nums.length) {
                // 满足条件，右边界右移
                while (right < nums.length && nums[right] == nums[right-1] + 1) {
                    right++;
                }

                // 不满足条件，计算上一次结果
                if (right - 1 == left) {
                    res.add(nums[left] + "");
                } else {
                    res.add(nums[left] + "->" + nums[right - 1]);
                }

                // 左指针指向右指针，右指针右移，开始寻找下一个答案
                left = right;
                right++;
            }
            return res;
        }

    }

}
