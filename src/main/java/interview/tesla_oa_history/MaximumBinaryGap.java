package interview.tesla_oa_history;

import org.junit.Test;

public class MaximumBinaryGap {

    /**
     * 把一个数表示成二进制，两个1之间的0的数量被称为gap，求最大的gap数
     * 比如：1041 -> 10000010001 -> maxGap = 5
     */

    public int getMaxBinaryGap(int num) {
        int maxGap = 0;    // 最大gap
        int count = 0;     // 当前正在数的0的个数
        boolean foundOne = false; // 是否找到第一个1

        while (num > 0) {
            // 取当前最后一位
            int bit = num & 1;

            if (bit == 1) {
                // 遇到1
                foundOne = true;
                maxGap = Math.max(maxGap, count);
                count = 0; // 重新开始数0
            } else {
                // 遇到0，只有已经找到1才计数
                if (foundOne) {
                    count++;
                }
            }
            num = num >> 1; // 右移一位
        }
        return maxGap;
    }

    @Test
    public void test() {
        System.out.println(getMaxBinaryGap(1041)); // 5
    }

}
