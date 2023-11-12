package leetcode;

public class _875minEatingSpeed {

    /**
     二分
     */
    public int minEatingSpeed(int[] piles, int h) {
        int maxSpeed = 0;
        for (int pile : piles) {
            maxSpeed = Math.max(maxSpeed, pile);
        }

        int left = 1, right = maxSpeed;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 耗时大于h，不满足，需要提高速度
            if (costHours(piles, mid) > h) {
                left = mid + 1;
            }
            // 耗时小于等于h，看mid能否再小点：如果不能，说明mid就是最小的，返回；如果可以，继续减小mid
            else {
                // 这里需要注意加上"mid - 1 > 0"的边界约束条件
                if (mid - 1 > 0 && costHours(piles, mid - 1) > h) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return 1;
    }

    private int costHours(int[] piles, int speed) {
        int sum = 0;
        for (int pile : piles) {
            // 向上取整
            // sum += pile % speed == 0 ? pile / speed : pile / speed + 1;

            // 向上取整也可以这样写
            sum += (pile + speed - 1) /speed;
        }
        return sum;
    }

}
