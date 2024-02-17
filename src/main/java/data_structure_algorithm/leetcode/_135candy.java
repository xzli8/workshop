package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _135candy {

    public static class Solution0 {

        /**
         贪心：先满足从右往左，再满足从左往右，然后取较小值
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int candy(int[] ratings) {
            int n = ratings.length;

            // 从左往右
            int[] left = new int[n];
            Arrays.fill(left, 1);
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i - 1]) left[i] = left[i - 1] + 1;
            }

            // 从右往左
            int[] right = new int[n];
            Arrays.fill(right, 1);
            for (int i = n - 2; i >= 0; i--) {
                if (ratings[i] > ratings[i + 1]) right[i] = right[i + 1] + 1;
            }

            // 取left和right的较大值分配
            int sum = 0;
            for (int i = 0; i < n; i++) sum += Math.max(left[i], right[i]);
            return sum;
        }

    }


    public static class Solution1 {

        /**
         贪心(类似题："406.根据身高重建队列")
         思路：多个条件，先满足其中一个，再满足另一个，最后选两个都满足的解。
         做法：先每个小孩分一个；然后从左往右遍历，将相邻的右孩子多分一个；再从左往右遍历，将相邻的左孩子多分一个；最后取左右最大的。
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
        public int candy(int[] ratings) {

            // 每个孩子都分一个
            int n = ratings.length;
            int[] left = new int[n], right = new int[n];
            Arrays.fill(left, 1);
            Arrays.fill(right, 1);

            // 从左往右遍历，满足右孩子（一定要从左往右遍历，这样当分配右孩子时，左孩子已经被处理，分配右孩子时无干扰）
            for (int i = 1; i < n; i++) {
                if (ratings[i] > ratings[i-1]) left[i] = left[i-1] + 1;
            }

            // 从右往左遍历，满足左孩子（一定要从右往左遍历，这样当分配左孩子时，右孩子已经被处理，分配左孩子时无干扰）
            for (int i = n-2; i >= 0; i--) {
                if (ratings[i] > ratings[i+1]) right[i] = right[i+1] + 1;
            }

            // 取两者的最大值，同时满足两个条件
            int count = 0;
            for (int i = 0; i < n; i++) {
                count += Math.max(left[i], right[i]);
            }
            return count;
        }

    }



}
