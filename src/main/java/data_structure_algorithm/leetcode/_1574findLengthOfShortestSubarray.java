package data_structure_algorithm.leetcode;

public class _1574findLengthOfShortestSubarray {

    public static class Solution1 {

        /**
         * 双指针：O(N), O(1)
         * ref: https://leetcode.doocs.org/lc/1574/
         */
        public int findLengthOfShortestSubarray(int[] arr) {
            int n = arr.length;
            int i = 0, j = n - 1;
            while (i + 1 < n && arr[i] <= arr[i + 1]) {
                ++i;
            }
            while (j - 1 >= 0 && arr[j - 1] <= arr[j]) {
                --j;
            }
            if (i >= j) {
                return 0;
            }
            int ans = Math.min(n - i - 1, j);
            for (int l = 0, r = j; l <= i; ++l) {
                while (r < n && arr[r] < arr[l]) {
                    ++r;
                }
                ans = Math.min(ans, r - l - 1);
            }
            return ans;
        }

    }

    public static class Solution2 {

        /**
         双指针
         ref:https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/solutions/2189149/dong-hua-yi-xie-jiu-cuo-liang-chong-xie-iijwz/
         分析：需要将区间分成三部分s[0, i]，s[i, j], s[j, n-1]，首尾区间都是非递减并且s[i] <= s[j]，中间s[i, j]是需要删除的区间
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int findLengthOfShortestSubarray(int[] arr) {
            // 从后往前找非递减区间
            int n = arr.length, right = n - 1;
            while (right > 0 && arr[right - 1] <= arr[right]) right--;
            if (right == 0) return 0;

            // 双指针找最短区间
            int minLen = right;
            for (int left = 0; left == 0 || arr[left - 1] <= arr[left]; left++) {
                while (right < n && arr[right] < arr[left]) right++;
                minLen = Math.min(minLen, right - left - 1);
            }
            return minLen;
        }

    }

}
