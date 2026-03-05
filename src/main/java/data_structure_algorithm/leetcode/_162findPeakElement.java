package data_structure_algorithm.leetcode;

public class _162findPeakElement {

    public static class Solution1 {

        /**
         二分搜索：O(logN), O(1)
         Note:(二段性)在以 mid 为分割点的数组上，根据 nums[mid] 与 nums[mid±1] 的大小关系，可以确定其中一段满足「必然有解」，另外一段不满足「必然有解」（可能有解，可能无解）
            1.如果nums[i] < nums[i+1]，峰值一定在i之后（nums[i+2]有两种可能，一种是小于nums[i+1]，那么nums[i+1]就是峰值；如果大于nums[i+1]，继续后推，因为nums[n]=-∞，所以nums[n-1]是峰值）
            2.如果nums[i] > nums[i+1]，峰值一定在i之前（同理）
         Ref: https://leetcode.cn/problems/find-peak-element/solutions/998441/gong-shui-san-xie-noxiang-xin-ke-xue-xi-qva7v/
         */
        public int findPeakElement(int[] nums) {
            int n = nums.length, left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if ((mid == 0 || nums[mid - 1] < nums[mid]) && (mid == n - 1 || nums[mid + 1] < nums[mid])) return mid;
                else if (mid == 0 || nums[mid - 1] < nums[mid]) left = mid + 1;     // 峰值在mid之前
                else right = mid - 1;   // 峰值在mid之后
            }
            return -1;
        }

    }

}
