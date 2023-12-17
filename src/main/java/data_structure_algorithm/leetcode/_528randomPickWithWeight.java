package data_structure_algorithm.leetcode;

import java.util.Random;

public class _528randomPickWithWeight {


    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(w);
     * int param_1 = obj.pickIndex();
     */

    public static class Solution1 {

        class Solution {

            /**
             前缀和 + 二分查找
             先计算数组w的前缀和数组preSum
             然后随机生成一个范围在[0, preSum[lastIndex]]内的随机数，在preSum数组中用二分查找定位相应元素
             最后返回元素对应下标
             */

            private int[] preSum;

            private Random random = new Random();

            public Solution(int[] w) {
                int n = w.length;
                preSum = new int[n + 1];
                preSum[0] = 0;
                for (int i = 1; i <= n; i++) {
                    preSum[i] = preSum[i - 1] + w[i - 1];
                }
            }

            public int pickIndex() {
                // 随机数生成
                int n = preSum.length;
                int ran = random.nextInt(preSum[n - 1]);

                // 二分查找：找最后一个小于等于ran的元素
                int left = 0, right = n - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (preSum[mid] <= ran) {
                        if (mid == n - 1 || preSum[mid + 1] > ran) {
                            return mid;
                        } else {
                            left = mid + 1;
                        }
                    } else {
                        right = mid - 1;
                    }
                }
                return -1;
            }

        }

    }

}
