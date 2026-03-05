package data_structure_algorithm.leetcode;

import java.util.Arrays;

public class _978maxTurbulenceSize {

    public static class Solution1 {

        /**
         SlideWindow: O(N), O(1)
         */
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length, left = 0, right = 0, maxLen = 1;
            while (right < n - 1) {
                // corner case(?)
                if (left == right) {
                    if (arr[left] == arr[left + 1]) left++;
                    right++;
                    maxLen = Math.max(maxLen, right - left + 1);
                } else {
                    while (right < n - 1 && (long) (arr[right] - arr[right - 1]) * (long) (arr[right] - arr[right + 1]) > 0) right++;
                    maxLen = Math.max(maxLen, right - left + 1);
                    left = right++;
                }
            }
            return maxLen;
        }

    }

    public static class Solution2 {

        /**
         DP: O(N), O(N)
         */
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length, maxLen = 1;

            // 定义状态：asd[i]和des[i]分别表示以arr[i]结尾上升(arr[i] > arr[i-1])和下降(arr[i] < arr[i-1])的最大长度
            int[] asd = new int[n], des = new int[n];

            // 初始状态
            Arrays.fill(asd, 1);
            Arrays.fill(des, 1);

            // 状态转移(ads[i]和des[i]只与前一个状态有关，可以用变量替换状态数组，将空间复杂度降低到O(1))
            for (int i = 1; i < n; i++) {
                asd[i] = arr[i] > arr[i - 1] ? des[i - 1] + 1 : 1;
                des[i] = arr[i] < arr[i - 1] ? asd[i - 1] + 1 : 1;
                maxLen = Math.max(maxLen, Math.max(asd[i], des[i]));
            }
            return maxLen;
        }

    }
    
}
