package dsa.leetcode;

import org.junit.Test;

public class _1011shipWithinDays {

    @Test
    public void test() {
//        int[] weights = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(shipWithinDays(weights, 5));

        int[] weights = new int[] {1,2,3,1,1};
        System.out.println(shipWithinDays(weights, 4));
    }

    /**
     二分查找
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
