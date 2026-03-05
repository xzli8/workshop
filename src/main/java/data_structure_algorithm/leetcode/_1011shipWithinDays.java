package data_structure_algorithm.leetcode;

public class _1011shipWithinDays {

    /**
     值域二分搜索: O(NlogU), O(1)
     */
    public int shipWithinDays(int[] weights, int days) {
        // 确定搜索的上下界
        int max = 0, sum = 0;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }
        int left = max, right = sum;

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
