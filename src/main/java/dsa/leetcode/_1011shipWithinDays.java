package dsa.leetcode;

public class _1011shipWithinDays {

    /**
     二分查找
     NOTE：难点在于上下界的确定
     时间复杂度：O(N)
     空间复杂度：O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        // 上下界根据题目已知条件确定(1 <= weights[i] <= 500, days <= weight.length)，也可以遍历找最大值确定上界
        int max = 0;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }
        int left = max, right = 500 * weights.length;

        // 找满足条件的最小值
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (countDays(weights, mid) <= days) {
                if (mid == max || countDays(weights, mid - 1) > days) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }

        // 不会走到这里
        return -1;
    }

    // 计算当运载能力为cap时需要的天数
    private int countDays(int[] weights, int cap) {
        int count = 1, sum = 0;
        for (int weight : weights) {
            sum += weight;
            if (sum > cap) {
                count++;
                sum = weight;
            }
        }
        return count;
    }

}
