package data_structure_algorithm.leetcode;

public class _410splitArray {

    /**
     二分(和1011题一模一样)
     分析：挖掘单调性(如果数组分段越多，那么各自和的最大值越小；如果分段越少，那么各自和的最大值越大)
     */
    public int splitArray(int[] nums, int k) {
        // 计算所有元素的和以及最大值，作为二分的上下边界
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }

        // 二分：找满足条件的最小值
        int left = max, right = sum;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int count = split(nums, mid);
            // 划分的段数小于等于k，说明mid还可以尝试再增加一点
            // 注意这里是小于等于k，而不是等于k，因为小于k能满足的话，再多划分几段，肯定也满足
            if (count <= k) {
                if (mid == max || split(nums, mid - 1) > k) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 将数组划分为非空连续子数组，且每个子数组和的最大值不超过max，返回划分出来的子数组数量
    // 和"1011shipWithinDays"的辅助函数"countDays"一模一样
    private int split(int[] nums, int max) {
        int count = 1, sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum > max) {
                count++;
                sum = num;
            }
        }
        return count;
    }

}
