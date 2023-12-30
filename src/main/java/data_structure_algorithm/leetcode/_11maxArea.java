package data_structure_algorithm.leetcode;

public class _11maxArea {

    public static class Solution1 {

        /**
         双指针
         贪心思路: 木桶容量由短板决定, 移动长板的话, 水面高度不可能再上升, 而宽度变小了,
         所以只有通过移动短板, 才有可能使水位上升.
         时间复杂度：O(N)
         空间复杂度：O(1)
         */
        public int maxArea(int[] height) {
            int maxArea = 0, left = 0, right = height.length - 1;
            while (left < right) {
                if (height[left] < height[right]) {
                    maxArea = Math.max(maxArea, height[left] * (right - left));
                    left++;
                } else {
                    maxArea = Math.max(maxArea, height[right] * (right - left));
                    right--;
                }
            }
            return maxArea;
        }

    }

}
