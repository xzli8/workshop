package dsa.leetcode;

public class _69mySqrt {

    /**
     牛顿迭代法
     */
//    public int mySqrt(int x) {
//        if (x == 0) {
//            return 0;
//        }
//
//        double x0 = x;
//        while (true) {
//            double x1 = 0.5 * (x0 + x / x0);
//            if (Math.abs(x1 - x0) < 1e-6) {
//                break;
//            }
//            x0 = x1;
//        }
//        return (int) x0;
//    }

    /**
     二分查找：找最后一个值小于等于目标值的元素
     时间复杂度：O(logN)
     空间复杂度：O(1)
     */
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        int left = 0, right = x;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 这里和下面的if判断，将"*"写成"/"是为了避免边界值溢出
            // 这里的if等价于：mid * mid > x
            if (mid > x / mid) {
                right = mid - 1;
            } else {
                // 这里的if等价于：(mid + 1) * (mid + 1) > x
                if ((mid + 1) > x / (mid + 1)) {
                    return mid;
                }
                else {
                    left = mid + 1;
                }
            }
        }
        // 不会走到这里
        return x;
    }

    /**
     *  求x的平方根，要求精确到小数点后n位
     */
    public double mySqrt(int x, int n) {
        // 先计算步长（注意，精确到小数点后n位，步长需要精确到第n+1位）
        double step = 1;
        for (int i = 0; i <= n; i++) {
            step /= 10;
        }

        // 然后二分查找（缩小边界时，左右边界每次移动一个步长）
        double left = 0, right = x;
        while (left <= right) {
            double mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid - step;
            } else {
                left = mid + step;

                // 这里的if为啥一直进不来？
//                if ((mid + step) > x / (mid + step)) {
//                    return mid;
//                } else {
//                    left = mid + step;
//                }
            }
        }
        return left;
    }

}
