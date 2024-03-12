package data_structure_algorithm.leetcode;

public class _845longestMountain {

    public static class Solution1 {

        /**
         动态规划：将每个位置都当成是山顶，计算其往两边最长的山脉数量
         时间复杂度：O(N)
         空间复杂度：O(N)
         */
         public int longestMountain(int[] arr) {
             int n = arr.length;
             if (n == 0) return 0;

             // 定义状态：left[i]表示第i个元素向左单调递减的元素个数，right[i]表示第i个元素向右单调递减的元素个数
             int[] left = new int[n], right = new int[n];
             for (int i = 1; i < n; i++) {
                 left[i] = arr[i - 1] < arr[i] ? left[i - 1] + 1 : 0;
             }
             for (int i = n - 2; i >= 0; i--) {
                 right[i] = arr[i + 1] < arr[i] ? right[i + 1] + 1 : 0;
             }

             // 计算最大值
             int res = 0;
             for (int i = 0; i < n; i++) {
                 if (left[i] > 0 && right[i] > 0) {
                     res = Math.max(res, left[i] + right[i] + 1);
                 }
             }
             return res;
         }

    }



    public static class Solution2 {

        /**
         滑动窗口
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int longestMountain(int[] arr) {
            int n = arr.length, res = 0, left = 0;
            while (left + 2 < n) {  // 山脉数组最少有3个元素
                int right = left + 1;
                // 先找山顶
                if (arr[left] < arr[left + 1]) {
                    while (right + 1 < n && arr[right] < arr[right + 1]) right++;
                    // 再找山脚
                    if (right < n - 1 && arr[right] > arr[right + 1]) {
                        while (right + 1 < n && arr[right] > arr[right + 1]) right++;
                        res = Math.max(res, right - left + 1);
                    } else {
                        right++;
                    }
                }
                left = right;
            }
            return res;
        }

    }

}
