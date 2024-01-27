package data_structure_algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LCR_180fileCombination {

    public static class Solution1 {

        /**
         滑动窗口(类似题："167.两数之和II")："和为s的连续正数序列"
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int[][] fileCombination(int target) {
            List<int[]> res = new ArrayList<>();
            int left = 1, right = 2, mid = (target + 1) / 2;
            while (left < mid) {
                int sum = (left + right) * (right - left + 1) / 2;
                if (sum == target) {
                    int[] tmp = new int[right - left + 1];
                    for (int i = left; i <= right; i++) tmp[i - left] = i;
                    res.add(tmp);
                    left++;
                    // right++;
                } else if (sum < target) {
                    right++;
                } else {
                    left++;
                }
            }
            return res.toArray(new int[res.size()][]);
        }

    }

}
