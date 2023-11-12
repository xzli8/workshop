import org.junit.Test;

public class MySqrt {

    @Test
    public void test() {
        System.out.println(mySqrt(2, 3));
        System.out.println(mySqrt(3, 3));
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
